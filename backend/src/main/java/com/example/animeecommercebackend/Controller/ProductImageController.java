package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Response.ProductImageResponseDto;
import com.example.animeecommercebackend.Service.Impl.ProductImageServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/productImage")
@Validated
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageServiceImpl productImageServiceImpl;


    // CREATE PRODUCT IMAGE
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<ProductImageResponseDto>> createProductImage(
            @RequestParam("productId")
            @Positive Long productId,

            @RequestParam("file")
            MultipartFile file,

            @RequestParam("altText")
            String altText,

            @RequestParam("displayOrder")
            @PositiveOrZero Integer displayOrder

    ) {

        ProductImageResponseDto productImage =
                productImageServiceImpl.createProductImage(
                        productId,
                        file,
                        altText,
                        displayOrder
                );

        ApiResponseDto<ProductImageResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Product Image Created Successfully",
                        productImage
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET PRODUCT IMAGE BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<ProductImageResponseDto>> getProductImageById(
            @PathVariable
            @Positive Long id
    ) {

        ProductImageResponseDto productImage =
                productImageServiceImpl.getProductImageById(id);

        ApiResponseDto<ProductImageResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Product Image Retrieved Successfully",
                        productImage
                );

        return ResponseEntity.ok(response);
    }

    // GET PRODUCT IMAGES BY PRODUCT ID
    @GetMapping("/productId/{productId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<List<ProductImageResponseDto>>> getProductImageByProductId(
            @PathVariable
            @Positive Long productId
    ) {

        List<ProductImageResponseDto> productImages =
                productImageServiceImpl.getImageByProductId(productId);

        ApiResponseDto<List<ProductImageResponseDto>> response =
                new ApiResponseDto<>(
                        true,
                        "Product Images Retrieved Successfully",
                        productImages
                );

        return ResponseEntity.ok(response);
    }

    // GET ALL PRODUCT IMAGES
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<List<ProductImageResponseDto>>> getAllProductImage() {

        List<ProductImageResponseDto> productImages =
                productImageServiceImpl.getAllProductImages();

        ApiResponseDto<List<ProductImageResponseDto>> response =
                new ApiResponseDto<>(
                        true,
                        "Product Images Retrieved Successfully",
                        productImages
                );

        return ResponseEntity.ok(response);
    }

    // UPDATE PRODUCT IMAGE
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<ProductImageResponseDto>> updateProductImage(
            @PathVariable
            @Positive Long id,

            @RequestParam("file")
            MultipartFile file,

            @RequestParam("altText")
            String altText,

            @RequestParam("displayOrder")
            @PositiveOrZero
            Integer displayOrder
    ) {

        ProductImageResponseDto productImage =
                productImageServiceImpl.updateProductImage(
                        id,
                        file,
                        altText,
                        displayOrder
                );

        ApiResponseDto<ProductImageResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Product Image Updated Successfully",
                        productImage
                );

        return ResponseEntity.ok(response);
    }

    // DELETE PRODUCT IMAGE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<Void>> deleteProductImage(
            @PathVariable
            @Positive Long id
    ) {

        productImageServiceImpl.deleteProductImage(id);

        ApiResponseDto<Void> response =
                new ApiResponseDto<>(
                        true,
                        "Product Image Deleted Successfully!",
                        null
                );

        return ResponseEntity.ok(response);
    }
}