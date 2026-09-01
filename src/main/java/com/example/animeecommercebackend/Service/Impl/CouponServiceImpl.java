package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.CouponRequestDto;
import com.example.animeecommercebackend.Dto.Response.CouponResponseDto;
import com.example.animeecommercebackend.Entity.Category;
import com.example.animeecommercebackend.Entity.Coupon;
import com.example.animeecommercebackend.Entity.Order;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.CouponMapper;
import com.example.animeecommercebackend.Repository.CouponRepository;
import com.example.animeecommercebackend.Repository.OrderRepository;
import com.example.animeecommercebackend.Repository.UserRepository;
import com.example.animeecommercebackend.Service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    @Override
    public CouponResponseDto createCoupon(CouponRequestDto dto) {
//        List<Order> orders = orderRepository.findAllById(dto.getOrderId());
//        if(orders.isEmpty()){
//            throw new RuntimeException("Product Id Not Found!");
//        }
        Coupon coupon = CouponMapper.toEntity(dto);

        Coupon saved = couponRepository.save(coupon);
        return CouponMapper.toResponse(saved);
    }

    @Override
    public CouponResponseDto getCouponById(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coupon Not Found!"));
        return CouponMapper.toResponse(coupon);
    }

    @Override
    public CouponResponseDto getCouponByCode(String code) {
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException("Coupon Code Not Found"));
        return CouponMapper.toResponse(coupon);
    }

    @Override
    public List<CouponResponseDto> getAllCoupon() {
        return couponRepository.findAll().stream().map(CouponMapper::toResponse).toList();
    }

    @Override
    public CouponResponseDto updateCoupon(Long id, CouponRequestDto dto) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coupon Not Found"));

        coupon.setCode(dto.getCode());
        coupon.setDescription(dto.getDescription());
        coupon.setDiscountType(dto.getDiscountType());
        coupon.setDiscountValue(dto.getDiscountValue());
        coupon.setMinimumOrderAmount(dto.getMinimumOrderAmount());
        coupon.setStartDate(dto.getStartDate());
        coupon.setEndDate(dto.getEndDate());
        coupon.setUsageLimit(dto.getUsageLimit());
        coupon.setActive(dto.getActive());

        Coupon saved = couponRepository.save(coupon);
        return CouponMapper.toResponse(saved);
    }

    @Override
    public void deleteCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coupon Not Found!"));
        couponRepository.delete(coupon);
    }
}
