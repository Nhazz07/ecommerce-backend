package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.CartRequestDto;
import com.example.animeecommercebackend.Dto.Response.CartResponseDto;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface CartService {
    CartResponseDto createCart(CartRequestDto dto) throws AccessDeniedException;
    CartResponseDto getCartById(Long id) throws AccessDeniedException;
    List<CartResponseDto> getAllCart();
    List<CartResponseDto> getCartByUserId(Long userId) throws AccessDeniedException;
    CartResponseDto updateCart(Long id, CartRequestDto dto) throws AccessDeniedException;
    void deleteCart(Long id) throws AccessDeniedException;
    CartResponseDto addProductVariant(Long cartId, Long productVariantId) throws AccessDeniedException;
    CartResponseDto removeProductVariant(Long cartId, Long productVariantId) throws AccessDeniedException;
}
