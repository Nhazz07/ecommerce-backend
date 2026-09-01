package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.CouponRequestDto;
import com.example.animeecommercebackend.Dto.Response.CouponResponseDto;

import java.util.List;

public interface CouponService {
    CouponResponseDto createCoupon(CouponRequestDto dto);
    CouponResponseDto getCouponById(Long id);
    CouponResponseDto getCouponByCode(String code);
    List<CouponResponseDto> getAllCoupon();
    CouponResponseDto updateCoupon(Long id, CouponRequestDto dto);
    void deleteCoupon(Long id);
}
