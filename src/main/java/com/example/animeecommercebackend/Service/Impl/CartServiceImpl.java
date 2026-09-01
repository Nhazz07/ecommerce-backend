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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private ProductVariantRepository productVariantRepository;

    @Override
    public CartResponseDto createCart(CartRequestDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found!"));
//        List<Product>products = productRepository.findAllById(List.of(dto.getProductId()));
//        if(products.isEmpty()){
//            throw new ResourceNotFoundException("No Product Found!!");
//        }

        List<ProductVariant> productVariants = productVariantRepository.findAllById(List.of(dto.getProductVariantId()).stream().toList());
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
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(("Cart Not Found!!")));
        return CartMapper.toResponse(cart);
    }

    @Override
    public List<CartResponseDto> getAllCart() {
        return cartRepository.findAll().stream().map(CartMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<CartResponseDto> getCartByUserId(Long userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        if(carts.isEmpty()){
            throw new ResourceNotFoundException("Cart Not Found!");
        }
        return carts.stream().map(CartMapper::toResponse).toList();
    }

    @Override
    public CartResponseDto updateCart(Long id, CartRequestDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
//        List<Product> products = productRepository.findAllById(List.of(dto.getProductId()));
//        if(products.isEmpty()){
//            throw new ResourceNotFoundException("No Product Found!!");
//        }
        List<ProductVariant> productVariants = productVariantRepository.findAllById(List.of(dto.getProductVariantId()));
        if(productVariants.isEmpty()){
            throw new ResourceNotFoundException("No Product Variant Found!");
        }
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart Not Found"));

        cart.setUser(user);

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
    public void deleteCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));
        cartRepository.delete(cart);
    }

    @Override
    public CartResponseDto addProductVariant(Long cartId, Long productVariantId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));

        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Variant Not Found!"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProductVariant(productVariant);
        cartItem.setQuantity(1);

        cart.getCartItems().add(cartItem);

        Cart updated = cartRepository.save(cart);

        return CartMapper.toResponse(updated);
    }

    @Override
    public CartResponseDto removeProductVariant(Long cartId, Long productVariantId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));

        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Variant Not Found"));

        cart.getCartItems().remove(productVariant);

        Cart updated = cartRepository.save(cart);

        return CartMapper.toResponse(updated);
    }
}
