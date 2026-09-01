package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.ProductRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto dto);
    ProductResponseDto getProductById(Long id);
    List<ProductResponseDto> getAllProduct();
    List<ProductResponseDto> getProductByCategoryId(Long categoryId);
    List<ProductResponseDto> getProductByBrandId(Long brandId);
    List<ProductResponseDto> getProductBySeriesId(Long seriesId);
    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);
    void deleteProduct(Long id);

}
