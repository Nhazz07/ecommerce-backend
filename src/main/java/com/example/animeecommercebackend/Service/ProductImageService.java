package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.ProductImageRequestDto;
import com.example.animeecommercebackend.Dto.Request.ProductRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductImageResponseDto;
import com.example.animeecommercebackend.Entity.ProductImages;

import java.util.List;

public interface ProductImageService {
    ProductImageResponseDto createProductImage(ProductImageRequestDto dto);
    ProductImageResponseDto getProductImageById(Long id);
    List<ProductImageResponseDto> getAllProductImages();
    List<ProductImageResponseDto> getImageByProductId(Long productId);
    ProductImageResponseDto updateProductImage(Long id, ProductImageRequestDto dto);
    void deleteProductImage(Long id);


}
