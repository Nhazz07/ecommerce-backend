package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Response.ProductImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {

    ProductImageResponseDto createProductImage(
            Long productId,
            MultipartFile file,
            String altText,
            Integer displayOrder
    );

    ProductImageResponseDto getProductImageById(Long id);

    List<ProductImageResponseDto> getAllProductImages();

    List<ProductImageResponseDto> getImageByProductId(Long productId);

    ProductImageResponseDto updateProductImage(
            Long id,
            MultipartFile file,
            String altText,
            Integer displayOrder
    );

    void deleteProductImage(Long id);
}