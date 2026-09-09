package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.ProductRequestDto;
import com.example.animeecommercebackend.Dto.Request.ProductVariantRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductVariantResponseDto;
import com.example.animeecommercebackend.Entity.Product;
import com.example.animeecommercebackend.Entity.ProductVariant;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.ProductVariantMapper;
import com.example.animeecommercebackend.Repository.ProductRepository;
import com.example.animeecommercebackend.Repository.ProductVariantRepository;
import com.example.animeecommercebackend.Service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    @Override
    public ProductVariantResponseDto createProductVariant(ProductVariantRequestDto dto) {
        Product product = productRepository.findById(dto.getProductId()).orElseThrow(() ->
                new ResourceNotFoundException("Product Not Found"));

        ProductVariant productVariant = ProductVariantMapper.toEntity(dto);

        productVariant.setProduct(product);
        ProductVariant saved = productVariantRepository.save(productVariant);

        return ProductVariantMapper.toResponse(saved);
    }

    @Override
    public ProductVariantResponseDto getProductVariantById(Long id) {
        ProductVariant productVariant = productVariantRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product Variant Not Found!"));
        return ProductVariantMapper.toResponse(productVariant);
    }

    @Override
    public List<ProductVariantResponseDto> getAllProductVariant() {
        return productVariantRepository.findAll().stream().map(ProductVariantMapper::toResponse).toList();
    }

    @Override
    public List<ProductVariantResponseDto> getVariantByProductId(Long productId) {
        List<ProductVariant> productVariants = productVariantRepository.findByProductId(productId);
        if(productVariants.isEmpty()){
            throw new ResourceNotFoundException("Product Variant Not Found in Product");
        }
        return productVariants.stream().map(ProductVariantMapper::toResponse).toList();
    }

    @Override
    public ProductVariantResponseDto updateProductVariant(Long id, ProductVariantRequestDto dto) {
        ProductVariant productVariant = productVariantRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product Not Found!"));

        productVariant.setSku(dto.getSku());
        productVariant.setName(dto.getName());
        productVariant.setPrice(dto.getPrice());
        productVariant.setSize(dto.getSize());
        productVariant.setColor(dto.getColor());

        ProductVariant updated = productVariantRepository.save(productVariant);
        return ProductVariantMapper.toResponse(updated);
    }

    @Override
    public void deleteProductVariant(Long id) {
        ProductVariant productVariant = productVariantRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product Variant Not Found"));

        productVariantRepository.delete(productVariant);
    }
}
