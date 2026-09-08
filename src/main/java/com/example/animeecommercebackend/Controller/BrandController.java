package com.example.animeecommercebackend.Controller;
import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Response.BrandResponseDto;
import com.example.animeecommercebackend.Service.Impl.BrandServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;

@RestController
@RequestMapping("/api/brand")
@Validated
@RequiredArgsConstructor
public class BrandController {

    private final BrandServiceImpl brandServiceImpl;

    // Customer Controller
    @GetMapping("/available")
    public ResponseEntity<ApiResponseDto<List<BrandResponseDto>>> getALLBrand(){

        List<BrandResponseDto> brands = brandServiceImpl.getAllBrand();

        ApiResponseDto<List<BrandResponseDto>> response = new ApiResponseDto<>(
                true,
                "Brand Retrieved Successfully!",
                brands
        );
        return ResponseEntity.ok(response);
    }
    // Admin Controller
    @PostMapping(consumes = MULTIPART_FORM_DATA)
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<BrandResponseDto>> createBrand(

            @RequestParam("file")
            MultipartFile file,

            @RequestParam("name")
            String name,

            @RequestParam("description")
            String description
    ){
        BrandResponseDto brand = brandServiceImpl.createBrand(
                file,
                name,
                description
        );

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
    @PutMapping(value = "/{id}", consumes = MULTIPART_FORM_DATA)
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<BrandResponseDto>> updateBrand(

            @PathVariable
            @Positive Long id,

            @RequestParam("file")
            MultipartFile file,

            @RequestParam("name")
            String name,

            @RequestParam("description")
            String description
            ){
        BrandResponseDto brand = brandServiceImpl.updateBrand(
                id,
                file,
                name,
                description
                );

        ApiResponseDto<BrandResponseDto> response = new ApiResponseDto<>(
                true,
                "Brand Updated Successfully!",
                brand
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
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
