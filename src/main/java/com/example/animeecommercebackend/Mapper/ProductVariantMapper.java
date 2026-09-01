package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.ProductVariantRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductVariantResponseDto;
import com.example.animeecommercebackend.Entity.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class ProductVariantMapper {

    public static ProductVariant toEntity(ProductVariantRequestDto dto
                                   ){
        ProductVariant productVariant = new ProductVariant();

        productVariant.setSku(dto.getSku());
        productVariant.setName(dto.getName());
        productVariant.setPrice(dto.getPrice());
        productVariant.setSize(dto.getSize());
        productVariant.setColor(dto.getColor());


        return productVariant;
    }

    public static ProductVariantResponseDto  toResponse(ProductVariant productVariant){
        ProductVariantResponseDto dto = new ProductVariantResponseDto();

        dto.setId(productVariant.getId());
        dto.setSku(productVariant.getSku());
        dto.setName(productVariant.getName());
        dto.setPrice(productVariant.getPrice());
        dto.setSize(productVariant.getSize());
        dto.setColor(productVariant.getColor());

        if(productVariant.getProduct() != null){
            dto.setProductId(productVariant.getProduct().getId());
            dto.setProductName(productVariant.getProduct().getProductName());
        }
        if(productVariant.getInventory() != null){
            dto.setInventoryId(productVariant.getInventory().getId());
        }
        if(productVariant.getOrders()!=null){
            dto.setOrderId(productVariant.getOrders().stream().map(Order::getId).toList());
        }else{
            dto.setOrderId(List.of());
        }
        if(productVariant.getCartItems() != null){
            dto.setCartId(productVariant.getCartItems().stream().map(CartItem::getId).toList());
        }else{
            dto.setCartId(List.of());
        }
        return dto;
    }
}





