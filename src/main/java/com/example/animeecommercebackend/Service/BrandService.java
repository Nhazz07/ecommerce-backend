package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.BrandRequestDto;
import com.example.animeecommercebackend.Dto.Response.BrandResponseDto;

import java.util.List;

public interface BrandService {

    BrandResponseDto createBrand(BrandRequestDto dto);
    BrandResponseDto getBrandById(Long id);
    List<BrandResponseDto> getAllBrand();
    BrandResponseDto updateBrand(Long id, BrandRequestDto dto);
    void deleteBrand(Long id);
}
