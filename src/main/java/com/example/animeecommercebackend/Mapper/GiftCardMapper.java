package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.GiftCardRequestDto;
import com.example.animeecommercebackend.Dto.Response.GiftCardResponseDto;
import com.example.animeecommercebackend.Entity.GiftCard;
import org.springframework.stereotype.Component;


@Component
public class GiftCardMapper {
    public static GiftCard toEntity(GiftCardRequestDto dto){
        GiftCard giftCard = new GiftCard();

        giftCard.setCode(dto.getCode());
        giftCard.setInitialBalance(dto.getInitialBalance());
        giftCard.setExpirationDate(dto.getExpirationDate());
        giftCard.setStatus(dto.getStatus());

        return giftCard;
    }

    public static GiftCardResponseDto toResponse(GiftCard giftCard){

        GiftCardResponseDto dto = new GiftCardResponseDto();

        dto.setId(giftCard.getId());
        dto.setCode(giftCard.getCode());
        dto.setInitialBalance(giftCard.getInitialBalance());
        dto.setRemainingBalance(giftCard.getRemainingBalance());
        dto.setStatus(giftCard.getStatus());

        if(giftCard.getUser() != null){
            dto.setUserId(giftCard.getUser().getId());
        }
        return dto;
    }
}

