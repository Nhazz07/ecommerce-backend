package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.PromotionRequestDto;
import com.example.animeecommercebackend.Dto.Response.PromotionResponseDto;
import com.example.animeecommercebackend.Entity.Promotion;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.PromotionMapper;
import com.example.animeecommercebackend.Repository.PromotionRepository;
import com.example.animeecommercebackend.Service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    @Override
    public PromotionResponseDto createPromotion(PromotionRequestDto dto) {
        Promotion promotion = PromotionMapper.toEntity(dto);

        Promotion saved = promotionRepository.save(promotion);
        return PromotionMapper.toResponse(saved);
    }

    @Override
    public PromotionResponseDto getPromotionById(Long id) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Promotion Not Found"));
        return PromotionMapper.toResponse(promotion);
    }

    @Override
    public List<PromotionResponseDto> getAllPromotion() {
        return promotionRepository.findAll().stream().map(PromotionMapper::toResponse).toList();
    }

    @Override
    public PromotionResponseDto getPromotionByProductId(Long productId) {

        Promotion promotion = promotionRepository.findByProductsId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion Not Found!"));

        return PromotionMapper.toResponse(promotion);
    }

    @Override
    public PromotionResponseDto updatePromotion(Long id, PromotionRequestDto dto) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Promotion Not Found"));

        promotion.setName(dto.getName());
        promotion.setDescription(dto.getDescription());
        promotion.setDiscountType(dto.getDiscountTypes());
        promotion.setDiscountValue(dto.getDiscountValue());
        promotion.setStartDate(dto.getStartDate());
        promotion.setEndDate(dto.getEndDate());
        promotion.setActive(dto.getActive());

        Promotion updated = promotionRepository.save(promotion);
        return PromotionMapper.toResponse(updated);
    }
    @Override
    public void deletePromotion(Long id) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Promotion Not Found"));

        promotionRepository.delete(promotion);
    }
}
