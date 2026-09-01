package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.WishlistRequestDto;
import com.example.animeecommercebackend.Dto.Response.WishlistResponseDto;
import com.example.animeecommercebackend.Entity.Product;
import com.example.animeecommercebackend.Entity.Wishlist;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.WishlistMapper;
import com.example.animeecommercebackend.Repository.ProductRepository;
import com.example.animeecommercebackend.Repository.WishlistRepository;
import com.example.animeecommercebackend.Service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    @Override
    public WishlistResponseDto createWishlist(WishlistRequestDto dto) {

        Wishlist wishlist = WishlistMapper.toEntity(dto);

        Wishlist saved = wishlistRepository.save(wishlist);

        return WishlistMapper.toResponse(saved);
    }

    @Override
    public WishlistResponseDto getWIshListById(Long id) {

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wishlist Not Found"));

        return WishlistMapper.toResponse(wishlist);
    }

    @Override
    public List<WishlistResponseDto> getAllWishlist() {

        return wishlistRepository.findAll()
                .stream()
                .map(WishlistMapper::toResponse)
                .toList();
    }

    @Override
    public List<WishlistResponseDto> getWishlistByUserId(Long userId) {

        List<Wishlist> wishlists =
                wishlistRepository.findByUserId(userId);

        if (wishlists.isEmpty()) {
            throw new ResourceNotFoundException("Wishlist Not Found");
        }

        return wishlists.stream()
                .map(WishlistMapper::toResponse)
                .toList();
    }

    @Override
    public WishlistResponseDto updateWishlist(
            Long id,
            WishlistRequestDto dto) {

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wishlist Not Found"));

        wishlist.setDescription(dto.getDescription());
        wishlist.setName(dto.getName());
        wishlist.setIsPublic(dto.getIsPublic());

        Wishlist updated = wishlistRepository.save(wishlist);

        return WishlistMapper.toResponse(updated);
    }

    @Override
    public void deleteWishlist(Long id) {

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wishlist Not Found"));

        wishlistRepository.delete(wishlist);
    }

    @Override
    public WishlistResponseDto addProduct(
            Long wishlistId,
            Long productId) {

        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wishlist Not Found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product Not Found"));

        wishlist.getProducts().add(product);

        Wishlist updated = wishlistRepository.save(wishlist);

        return WishlistMapper.toResponse(updated);
    }

    @Override
    public WishlistResponseDto removeProduct(
            Long wishlistId,
            Long productId) {

        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wishlist Not Found"));

        wishlist.getProducts()
                .removeIf(product ->
                        product.getId().equals(productId));

        Wishlist updated = wishlistRepository.save(wishlist);

        return WishlistMapper.toResponse(updated);
    }
}