package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.BrandRequestDto;
import com.example.animeecommercebackend.Dto.Response.BrandResponseDto;
import com.example.animeecommercebackend.Entity.Brand;
import com.example.animeecommercebackend.Service.Impl.BrandServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandServiceImpl brandServiceImpl;

    public BrandController(BrandServiceImpl brandServiceImpl) {
        this.brandServiceImpl = brandServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<BrandResponseDto>> createBrand(@RequestBody @Valid BrandRequestDto dto){
        BrandResponseDto brand = brandServiceImpl.createBrand(dto);

        ApiResponseDto<BrandResponseDto> response = new ApiResponseDto<>(
                true,
                "Brand Created Successfully!",
                brand
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<BrandResponseDto>> getBrandById(@PathVariable @Positive Long id){
        BrandResponseDto brand = brandServiceImpl.getBrandById(id);

        ApiResponseDto<BrandResponseDto> response = new ApiResponseDto<>(
                true,
                "Brand Retrieved Successfully",
                brand
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<BrandResponseDto>>> getAllBrand(){
        List<BrandResponseDto> brand = brandServiceImpl.getAllBrand();

        ApiResponseDto<List<BrandResponseDto>> response = new ApiResponseDto<>(
                true,
                "Brand Retrieved Successfully!!",
                brand
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<BrandResponseDto>> updateBrand(@PathVariable @Positive Long id, @RequestBody @Valid BrandRequestDto dto){
        BrandResponseDto brand = brandServiceImpl.updateBrand(id, dto);

        ApiResponseDto<BrandResponseDto> response = new ApiResponseDto<>(
                true,
                "Brand Updated Successfully!",
                brand
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteBrand(@PathVariable @Positive Long id){
        brandServiceImpl.deleteBrand(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Brand Deleted Successfully!",
                null
        );
        return ResponseEntity.ok(response);
    }
}
