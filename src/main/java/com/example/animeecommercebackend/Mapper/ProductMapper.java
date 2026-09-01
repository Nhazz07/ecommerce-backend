package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.ProductRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductResponseDto;
import com.example.animeecommercebackend.Entity.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
public class ProductMapper {
    public static Product toEntity(ProductRequestDto dto
                            ){
        Product product = new Product();

        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setSku(dto.getSku());
        product.setPrice(dto.getPrice());
        product.setStatus(dto.getStatus());
        product.setReleaseDate(dto.getReleaseDate());

        return product;
    }

    public static ProductResponseDto toResponse(Product product){
        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(product.getId());
        dto.setName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setSku(product.getSku());
        dto.setPrice(product.getPrice());
        dto.setStatus(product.getStatus());
        dto.setReleaseDate(product.getReleaseDate());


        if(product.getCategory() != null){
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }
        if(product.getBrand() !=  null){
            dto.setBrandId(product.getBrand().getId());
            dto.setBrandName(product.getBrand().getName());
        }
        if(product.getSeries() != null){
            dto.setSeriesId(product.getSeries().getId());
            dto.setSeriesName(product.getSeries().getName());
        }
        if(product.getProductVariants() != null){
            dto.setProductVariantIds(product.getProductVariants().stream().map(ProductVariant::getId).toList());
        }else{
            dto.setProductVariantIds(List.of());
        }

        if(product.getProductImages() !=  null){
            dto.setProductImagesId(product.getProductImages().stream().map(ProductImages::getId).toList());
        }else{
            dto.setProductImagesId(List.of());
        }
        if(product.getReviews() != null){
            dto.setReviewId(product.getReviews().stream().map(Review::getId).toList());
        }else{
            dto.setReviewId(List.of());
        }
        return dto;
    }
}


