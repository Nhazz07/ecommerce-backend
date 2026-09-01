package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.CartRequestDto;
import com.example.animeecommercebackend.Dto.Response.CartResponseDto;

import java.util.List;

public interface CartService {
    CartResponseDto createCart(CartRequestDto dto);
    CartResponseDto getCartById(Long id);
    List<CartResponseDto> getAllCart();
    List<CartResponseDto> getCartByUserId(Long userId);
    CartResponseDto updateCart(Long id, CartRequestDto dto);
    void deleteCart(Long id);
    CartResponseDto addProductVariant(Long cartId, Long productVariantId);
    CartResponseDto removeProductVariant(Long cartId, Long productVariantId);
}
