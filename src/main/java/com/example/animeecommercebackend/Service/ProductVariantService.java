package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.ProductVariantRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductVariantResponseDto;

import java.util.List;

public interface ProductVariantService {
    ProductVariantResponseDto createProductVariant(ProductVariantRequestDto dto);
    ProductVariantResponseDto getProductVariantById(Long id);
    List<ProductVariantResponseDto> getAllProductVariant();
    List<ProductVariantResponseDto> getVariantByProductId(Long productId);
    ProductVariantResponseDto updateProductVariant(Long id, ProductVariantRequestDto dto);
    void deleteProductVariant(Long id);
}
