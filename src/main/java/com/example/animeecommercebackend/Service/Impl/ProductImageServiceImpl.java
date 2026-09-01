package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.ProductImageRequestDto;
import com.example.animeecommercebackend.Dto.Request.ProductRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductImageResponseDto;
import com.example.animeecommercebackend.Entity.Product;
import com.example.animeecommercebackend.Entity.ProductImages;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.ProductImagesMapper;
import com.example.animeecommercebackend.Mapper.ProductMapper;
import com.example.animeecommercebackend.Repository.ProductImageRepository;
import com.example.animeecommercebackend.Repository.ProductVariantRepository;
import com.example.animeecommercebackend.Service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    @Override
    public ProductImageResponseDto createProductImage(ProductImageRequestDto dto) {
        ProductImages productImages = ProductImagesMapper.toEntity(dto);

        ProductImages saved = productImageRepository.save(productImages);

        return ProductImagesMapper.toResponse(saved);
    }

    @Override
    public ProductImageResponseDto getProductImageById(Long id) {
        ProductImages productImages = productImageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Image Not Found!"));
        return ProductImagesMapper.toResponse(productImages);
    }

    @Override
    public List<ProductImageResponseDto> getAllProductImages() {
        return productImageRepository.findAll().stream().map(ProductImagesMapper::toResponse).toList();
    }

    @Override
    public List<ProductImageResponseDto> getImageByProductId(Long productId) {
        List<ProductImages> productImages = productImageRepository.findByProductId(productId);
        if(productImages.isEmpty()){
            throw new ResourceNotFoundException("Product Images Not Found!");
        }
        return productImageRepository.findAll().stream().map(ProductImagesMapper::toResponse).toList();
    }

    @Override
    public ProductImageResponseDto updateProductImage(Long id, ProductImageRequestDto dto) {
        ProductImages productImages = productImageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Images Not Found!!"));

        productImages.setAltText(dto.getAltText());
        productImages.setImageUrl(dto.getUrlImages());
        productImages.setDisplayOrder(dto.getDisplayOrder());

        ProductImages updated = productImageRepository.save(productImages);
        return ProductImagesMapper.toResponse(updated);
    }

    @Override
    public void deleteProductImage(Long id) {
        ProductImages productImages = productImageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Images Not Found!!"));

        productImageRepository.delete(productImages);
    }
}
