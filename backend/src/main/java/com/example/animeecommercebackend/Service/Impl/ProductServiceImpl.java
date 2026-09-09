package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.ProductRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductResponseDto;
import com.example.animeecommercebackend.Entity.Brand;
import com.example.animeecommercebackend.Entity.Category;
import com.example.animeecommercebackend.Entity.Product;
import com.example.animeecommercebackend.Entity.Series;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.ProductMapper;
import com.example.animeecommercebackend.Repository.BrandRepository;
import com.example.animeecommercebackend.Repository.CategoryRepository;
import com.example.animeecommercebackend.Repository.ProductRepository;
import com.example.animeecommercebackend.Repository.SeriesRepository;
import com.example.animeecommercebackend.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final SeriesRepository seriesRepository;


    @Override
    public ProductResponseDto createProduct(ProductRequestDto dto) {

        // Find Category
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category Not Found!"));

        // Find Brand
        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Brand Not Found!"));

        // Find Series
        Series series = seriesRepository.findById(dto.getSeriesId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Series Not Found!"));

        // Convert DTO -> Entity
        Product product = ProductMapper.toEntity(dto);

        // Set relationships
        product.setCategory(category);
        product.setBrand(brand);
        product.setSeries(series);

        // Save
        Product saved = productRepository.save(product);

        // Entity -> Response DTO
        return ProductMapper.toResponse(saved);
    }


    @Override
    public ProductResponseDto getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found!"));

        return ProductMapper.toResponse(product);
    }

    @Override
    public Page<ProductResponseDto> getAllProduct(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductMapper::toResponse);
    }



    @Override
    public List<ProductResponseDto> getProductByCategoryId(Long categoryId) {

        List<Product> products = productRepository.findByCategoryId(categoryId);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No Product Found For This Category"
            );
        }

        return products.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }


    @Override
    public List<ProductResponseDto> getProductByBrandId(Long brandId) {

        List<Product> products = productRepository.findByBrandId(brandId);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No Product Found For This Brand"
            );
        }

        return products.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }


    @Override
    public List<ProductResponseDto> getProductBySeriesId(Long seriesId) {

        List<Product> products = productRepository.findBySeriesId(seriesId);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No Product Found For This Series"
            );
        }

        return products.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }


    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));


        // Find Category
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category Not Found!"));


        // Find Brand
        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Brand Not Found!"));


        // Find Series
        Series series = seriesRepository.findById(dto.getSeriesId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Series Not Found!"));


        // Update basic fields
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setSku(dto.getSku());
        product.setPrice(dto.getPrice());
        product.setStatus(dto.getStatus());
        product.setReleaseDate(dto.getReleaseDate());


        // Update relationships
        product.setCategory(category);
        product.setBrand(brand);
        product.setSeries(series);


        Product updated = productRepository.save(product);

        return ProductMapper.toResponse(updated);
    }


    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));

        productRepository.delete(product);
    }
}