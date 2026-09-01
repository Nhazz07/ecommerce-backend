package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.BrandRequestDto;
import com.example.animeecommercebackend.Dto.Response.BrandResponseDto;
import com.example.animeecommercebackend.Entity.Brand;
import com.example.animeecommercebackend.Entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandMapper {
    public static Brand toEntity(BrandRequestDto dto
                          ){
        Brand brand = new Brand();

        brand.setName(dto.getName());
        brand.setDescription(dto.getDescription());
        brand.setLogoUrl(dto.getLogoUrl());

        return brand;
    }

    public static BrandResponseDto toResponse(Brand brand){
        BrandResponseDto dto = new BrandResponseDto();

        dto.setId(brand.getId());
        dto.setDescription(brand.getDescription());
        dto.setName(brand.getName());
        dto.setLogoUrl(brand.getLogoUrl());

        if(brand.getProducts() != null){
            dto.setProductId(brand.getProducts().stream().map(Product::getId).toList());
        }else{
            dto.setProductId(List.of());
        }

        return dto;
    }
}
