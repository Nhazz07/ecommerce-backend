package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.CouponRequestDto;
import com.example.animeecommercebackend.Dto.Response.CouponResponseDto;
import com.example.animeecommercebackend.Entity.Coupon;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.CouponMapper;
import com.example.animeecommercebackend.Repository.CouponRepository;
import com.example.animeecommercebackend.Repository.OrderRepository;
import com.example.animeecommercebackend.Repository.UserRepository;
import com.example.animeecommercebackend.Service.CouponService;
import lombok.RequiredArgsConstructor;
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

        Coupon coupon = CouponMapper.toEntity(dto);

        coupon.setUsedCount(0);

        Coupon saved = couponRepository.save(coupon);

        return CouponMapper.toResponse(saved);
    }

    @Override
    public CouponResponseDto getCouponById(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Coupon Not Found!"));
        return CouponMapper.toResponse(coupon);
    }

    @Override
    public CouponResponseDto getCouponByCode(String code) {
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() ->
                new ResourceNotFoundException("Coupon Code Not Found"));
        return CouponMapper.toResponse(coupon);
    }

    @Override
    public List<CouponResponseDto> getAllCoupon() {
        return couponRepository.findAll().stream().map(CouponMapper::toResponse).toList();
    }

    @Override
    public CouponResponseDto updateCoupon(Long id, CouponRequestDto dto) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Coupon Not Found"));

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
        Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Coupon Not Found!"));
        couponRepository.delete(coupon);
    }

    @Override
    public BigDecimal calculateDiscount(Long couponId, BigDecimal orderAmount) {
        Coupon coupon = couponRepository
                .findById(couponId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coupon Not Found!")
                        );
        if(!coupon.getActive()){
            throw new RuntimeException("Coupon is not active");
        }

        LocalDateTime now = LocalDateTime.now();

        if(now.isBefore(coupon.getStartDate()) || now.isAfter(coupon.getEndDate())){
            throw new RuntimeException("Coupon has expired or not active yet");
        }

        if(orderAmount.compareTo(coupon.getMinimumOrderAmount()) < 0){
            throw new RuntimeException("Order does not meet minimum amount");
        }
        if(coupon.getUsageLimit() != null &&
        coupon.getUsedCount() >= coupon.getUsageLimit()
        ){
            throw new RuntimeException("Coupon usage limit reached");
        }
        if(coupon.getDiscountType().equalsIgnoreCase("PERCENTAGE")){
            return orderAmount
                    .multiply(coupon.getDiscountValue())
                    .divide(BigDecimal.valueOf(100));
        }
        if(coupon.getDiscountType().equalsIgnoreCase("FIXED")){
            return coupon.getDiscountValue()
                    .min(orderAmount);
        }
        throw new RuntimeException("Invalid Coupon Discount Type!");
    }

    @Override
    public void increaseUsage(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new
                                ResourceNotFoundException("Coupon Not Found!")
                        );
        coupon.setUsedCount(coupon.getUsedCount() + 1);
        couponRepository.save(coupon);
    }
}
