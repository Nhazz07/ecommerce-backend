package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.BrandRequestDto;
import com.example.animeecommercebackend.Dto.Response.BrandResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BrandService {

    BrandResponseDto createBrand(
            Long brandId,
            MultipartFile file,
            String name,
            String description);
    BrandResponseDto getBrandById(Long id);
    List<BrandResponseDto> getAllBrand();
    BrandResponseDto updateBrand(Long id,
                                 MultipartFile file,
                                 String name,
                                 String description);
    void deleteBrand(Long id);
}
