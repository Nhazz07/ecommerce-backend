package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.CategoryRequestDto;
import com.example.animeecommercebackend.Dto.Response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto dto);
    CategoryResponseDto getCategoryById(Long id);
    List<CategoryResponseDto> getAllCategory();
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto);
    void deleteCategory(Long id);
}
