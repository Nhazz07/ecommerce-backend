package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.CartRequestDto;
import com.example.animeecommercebackend.Dto.Response.CartResponseDto;
import com.example.animeecommercebackend.Entity.*;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.CartMapper;
import com.example.animeecommercebackend.Repository.CartRepository;
import com.example.animeecommercebackend.Repository.ProductRepository;
import com.example.animeecommercebackend.Repository.ProductVariantRepository;
import com.example.animeecommercebackend.Repository.UserRepository;
import com.example.animeecommercebackend.Service.CartService;
import com.example.animeecommercebackend.Service.CurrentUserService;
import org.springframework.security.access.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final CurrentUserService currentUserService;

    @Override
    public CartResponseDto createCart(
            CartRequestDto dto) throws AccessDeniedException {
        User currentUser = currentUserService.getCurrentUser();
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("User Not Found!"));

        // make sure customer can only create a cart by themselves
        if(!user.getId().equals(currentUser.getId())){
            throw new AccessDeniedException("You Cannot create a cart for another user");
        }
        List<ProductVariant> productVariants =
                productVariantRepository.findAllById(
                        List.of(dto.getProductVariantId()).stream().toList()
                );
        if(productVariants.isEmpty()){
            throw new ResourceNotFoundException("No Product Variant Found!");
        }
        Cart cart = CartMapper.toEntity(dto);

        cart.setUser(user);

        for (ProductVariant productVariant : productVariants) {

            CartItem cartItem = new CartItem();

            cartItem.setCart(cart);
            cartItem.setProductVariant(productVariant);
            cartItem.setQuantity(1);

            cart.getCartItems().add(cartItem);
        }

        Cart savedCart = cartRepository.save(cart);

        return CartMapper.toResponse(savedCart);
    }

    @Override
    public CartResponseDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(("Cart Not Found!!")));
        checkCartOwnership(cart);
        return CartMapper.toResponse(cart);
    }


    @Override
    public List<CartResponseDto> getAllCart() {
        User currentUser = currentUserService.getCurrentUser();

        List<Cart> carts = cartRepository.findByUserId(currentUser.getId());

        if(carts.isEmpty()){
            throw new ResourceNotFoundException("Cart Not Found!!");
        }
        return carts
                .stream()
                .map(CartMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartResponseDto> getCartByUserId(
            Long userId) throws AccessDeniedException {
        User currentUser = currentUserService.getCurrentUser();
        if(!currentUser.getId().equals(userId)){
            throw new AccessDeniedException("You cannot access another user's cart");
        }

        List<Cart> carts = cartRepository.findByUserId(userId);
        if(carts.isEmpty()){
            throw new ResourceNotFoundException("Cart Not Found!");
        }

        return carts
                .stream()
                .map(CartMapper::toResponse)
                .toList();
    }

    @Override
    public CartResponseDto updateCart(Long id, CartRequestDto dto) {

        Cart cart = cartRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Cart Not Found")
                );
        checkCartOwnership(cart);
        List<ProductVariant> productVariants = productVariantRepository.findAllById(
                List.of(dto.getProductVariantId())
        );
        if(productVariants.isEmpty()){
            throw new ResourceNotFoundException("No Product Variant Found!");
        }

        for (ProductVariant productVariant : productVariants) {

            CartItem cartItem = new CartItem();

            cartItem.setCart(cart);
            cartItem.setProductVariant(productVariant);
            cartItem.setQuantity(1);

            cart.getCartItems().add(cartItem);
        }

        Cart updated = cartRepository.save(cart);

        return CartMapper.toResponse(updated);
    }

    @Override
    public void deleteCart(Long id) throws AccessDeniedException {
        Cart cart = cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart Not Found"));
        checkCartOwnership(cart);
        cartRepository.delete(cart);
    }

    @Override
    public CartResponseDto addProductVariant(Long cartId, Long productVariantId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart Not Found"));

        checkCartOwnership(cart);

        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Variant Not Found!"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProductVariant(productVariant);
        cartItem.setQuantity(1);

        cart.getCartItems().add(cartItem);

        Cart updated = cartRepository.save(cart);

        return CartMapper.toResponse(updated);
    }

    @Override
    public CartResponseDto removeProductVariant(Long cartId, Long productVariantId)  {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart Not Found"));
        checkCartOwnership(cart);
        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProductVariant().getId().equals(productVariantId)
                )
                .findFirst()
                .orElseThrow(()->
                        new ResourceNotFoundException("Product Variant is not in the cart")
                        );
        cart.getCartItems().remove(cartItem);
        Cart updated = cartRepository.save(cart);

        return CartMapper.toResponse(updated);
    }

    private void checkCartOwnership(Cart cart) {
        User currentUser = currentUserService.getCurrentUser();

        if(!cart.getUser().getId().equals(currentUser.getId())){
            throw new AccessDeniedException("You cannot access this cart");
        }
    }
}
