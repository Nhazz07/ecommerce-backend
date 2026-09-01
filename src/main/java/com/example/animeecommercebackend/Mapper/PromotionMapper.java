package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.PromotionRequestDto;
import com.example.animeecommercebackend.Dto.Response.PromotionResponseDto;
import com.example.animeecommercebackend.Entity.Product;
import com.example.animeecommercebackend.Entity.Promotion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
public class PromotionMapper {
    public static Promotion toEntity(PromotionRequestDto dto
                              ){
        Promotion promotion = new Promotion();

        promotion.setName(dto.getName());
        promotion.setDescription(dto.getDescription());
        promotion.setDiscountType(dto.getDiscountTypes());
        promotion.setDiscountValue(dto.getDiscountValue());
        promotion.setStartDate(dto.getStartDate());
        promotion.setEndDate(dto.getEndDate());
        promotion.setActive(dto.getActive());


        return promotion;
    }

    public static PromotionResponseDto toResponse(Promotion promotion){
        PromotionResponseDto dto = new PromotionResponseDto();

        dto.setId(promotion.getId());
        dto.setName(promotion.getName());
        dto.setDescription(promotion.getDescription());
        dto.setDiscountTypes(promotion.getDiscountType());
        dto.setDiscountValue(promotion.getDiscountValue());
        dto.setStartDate(promotion.getStartDate());
        dto.setStartDate(promotion.getStartDate());

        if(promotion.getProducts() != null){
            dto.setProductIds(promotion.getProducts().stream().map(Product::getId).toList());
        }else{
           dto.setProductIds(List.of());
        }
        return dto;
    }

}
