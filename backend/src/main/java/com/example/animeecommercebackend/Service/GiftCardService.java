package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.GiftCardRequestDto;
import com.example.animeecommercebackend.Dto.Response.GiftCardResponseDto;

import java.util.List;

public interface GiftCardService {
    GiftCardResponseDto createGiftCard(GiftCardRequestDto dto);
    GiftCardResponseDto getGiftCardById(Long id);
    List<GiftCardResponseDto> getAllGiftCard();
    List<GiftCardResponseDto> getGiftCardsByUserId(Long userId);
    GiftCardResponseDto updateGiftCard(Long id, GiftCardRequestDto dto);
    void deleteGiftCard(Long id);
}
