package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.BrandRequestDto;
import com.example.animeecommercebackend.Dto.Response.BrandResponseDto;
import com.example.animeecommercebackend.Entity.Brand;
import com.example.animeecommercebackend.Entity.Product;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.BrandMapper;
import com.example.animeecommercebackend.Repository.BrandRepository;
import com.example.animeecommercebackend.Repository.ProductRepository;
import com.example.animeecommercebackend.Service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    @Override
    public BrandResponseDto createBrand(BrandRequestDto dto) {

        Brand brand = BrandMapper.toEntity(dto);



        Brand saved = brandRepository.save(brand);
        return BrandMapper.toResponse(saved);
    }

    @Override
    public BrandResponseDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand Not Found!"));
        return BrandMapper.toResponse(brand);
    }

    @Override
    public List<BrandResponseDto> getAllBrand() {
        return brandRepository.findAll().stream().map(BrandMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto updateBrand(Long id, BrandRequestDto dto) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand Not Found!1"));



        brand.setName(dto.getName());
        brand.setLogoUrl(dto.getLogoUrl());
        brand.setDescription(dto.getDescription());

        Brand updated = brandRepository.save(brand);
        return BrandMapper.toResponse(updated);
    }

    @Override
    public void deleteBrand(Long id) {
       Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand Not Found!!"));
       brandRepository.delete(brand);
    }
}
