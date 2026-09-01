package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.PromotionRequestDto;
import com.example.animeecommercebackend.Dto.Response.PromotionResponseDto;
import com.example.animeecommercebackend.Entity.Promotion;

import java.util.List;

public interface PromotionService {
    PromotionResponseDto createPromotion(PromotionRequestDto dto);
    PromotionResponseDto getPromotionById(Long id);
    List<PromotionResponseDto> getAllPromotion();
    PromotionResponseDto getPromotionByProductId(Long productId);
    PromotionResponseDto updatePromotion(Long id, PromotionRequestDto dto);
    void deletePromotion(Long id);
}
