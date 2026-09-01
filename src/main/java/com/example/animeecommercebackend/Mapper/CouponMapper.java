package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.CouponRequestDto;
import com.example.animeecommercebackend.Dto.Response.CouponResponseDto;
import com.example.animeecommercebackend.Entity.Coupon;
import com.example.animeecommercebackend.Entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponMapper {

    public static Coupon toEntity(CouponRequestDto dto
                           ){
        Coupon coupon = new Coupon();

        coupon.setCode(dto.getCode());
        coupon.setDescription(dto.getDescription());
        coupon.setDiscountType(dto.getDiscountType());
        coupon.setDiscountValue(dto.getDiscountValue());
        coupon.setMinimumOrderAmount(dto.getMinimumOrderAmount());
        coupon.setStartDate(dto.getStartDate());
        coupon.setEndDate(dto.getEndDate());
        coupon.setUsageLimit(dto.getUsageLimit());
        coupon.setActive(dto.getActive());


        return coupon;
    }
    public static CouponResponseDto toResponse(Coupon coupon){
        CouponResponseDto dto = new CouponResponseDto();

        dto.setId(coupon.getId());
        dto.setCode(coupon.getCode());
        dto.setDescription(coupon.getDescription());
        dto.setDiscountType(coupon.getDiscountType());
        dto.setDiscountValue(coupon.getDiscountValue());
        dto.setMinimumOrderAmount(coupon.getMinimumOrderAmount());
        dto.setStartDate(coupon.getStartDate());
        dto.setEndDate(coupon.getEndDate());
        dto.setUsageLimit(coupon.getUsageLimit());
        dto.setActive(coupon.getActive());

        if(coupon.getOrders() != null){
            dto.setOrderId(coupon.getOrders().stream().map(Order::getId).toList());
        }else{
            dto.setOrderId(List.of());
        }
        return dto;
    }
}

