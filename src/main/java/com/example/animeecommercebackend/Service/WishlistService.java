package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.WishlistRequestDto;
import com.example.animeecommercebackend.Dto.Response.WishlistResponseDto;

import java.util.List;

public interface WishlistService {

    WishlistResponseDto createWishlist(WishlistRequestDto dto);

    WishlistResponseDto getWIshListById(Long id);

    List<WishlistResponseDto> getAllWishlist();

    List<WishlistResponseDto> getWishlistByUserId(Long userId);

    WishlistResponseDto updateWishlist(Long id, WishlistRequestDto dto);

    void deleteWishlist(Long id);

    WishlistResponseDto addProduct(Long wishlistId, Long productId);

    WishlistResponseDto removeProduct(Long wishlistId, Long productId);
}