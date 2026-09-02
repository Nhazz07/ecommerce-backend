package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.OrderRequestDto;
import com.example.animeecommercebackend.Dto.Response.OrderResponseDto;
import com.example.animeecommercebackend.Entity.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class OrderMapper {

    public static Order toEntity(OrderRequestDto dto
                          ){
        Order order = new Order();

        order.setShippingAddress(dto.getShippingAddress());



        return order;
    }

    public static OrderResponseDto toResponse(Order order){
        OrderResponseDto dto = new OrderResponseDto();

        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.isStatus());

        dto.setTotalAmount(order.getTotalAmount());
        dto.setSubTotal(order.getSubTotal());
        dto.setShippingFee(order.getShippingFee());
        dto.setDiscountAmount(order.getDiscountAmount());

        dto.setShippingAddress(order.getShippingAddress());

        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        if(order.getUser() != null){
            dto.setUserId((order.getUser().getId()));
        }
        if(order.getProductVariants() != null){
            dto.setProductVariantIds(order.getProductVariants().stream().map(ProductVariant::getId).toList());
        }else{
            dto.setProductVariantIds(List.of());
        }
        if(order.getCoupon() != null){
           dto.setCouponId(order.getCoupon().getId());
        }
        if(order.getPayment() != null){
            dto.setPaymentId(order.getPayment().getId());
        }
        if(order.getShipment() != null){
            dto.setShipmentId(order.getShipment().getId());
        }
        if(order.getReturns() != null){
            dto.setReturnsId(order.getReturns().stream().map(Return::getId).toList());
        }else{
            dto.setReturnsId(List.of());
        }
        return dto;
    }


}
