package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.CategoryRequestDto;
import com.example.animeecommercebackend.Dto.Response.CartResponseDto;
import com.example.animeecommercebackend.Dto.Response.CategoryResponseDto;
import com.example.animeecommercebackend.Entity.Category;
import com.example.animeecommercebackend.Entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public static Category toEntity(CategoryRequestDto dto
                             ){
        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setImageUrl(dto.getImageUrl());

        return category;
    }
    public static CategoryResponseDto toResponse(Category category){
        CategoryResponseDto dto = new CategoryResponseDto();

        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setImageUrl(category.getImageUrl());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());
        if(category.getProducts() != null){
            dto.setProductId(category.getProducts().stream().map(Product::getId).toList());
        }else{
            dto.setProductId(List.of());
        }

        return dto;
    }
}

