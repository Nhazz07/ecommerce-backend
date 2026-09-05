package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.ProductRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductResponseDto;
import com.example.animeecommercebackend.Service.Impl.ProductImageServiceImpl;
import com.example.animeecommercebackend.Service.Impl.ProductServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> createProduct(
            @RequestBody @Valid ProductRequestDto dto){
        ProductResponseDto product = productServiceImpl.createProduct(dto);

        ApiResponseDto<ProductResponseDto> response = new ApiResponseDto<>(
                true,
                "Product Created Successfully!",
                product
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<Page<ProductResponseDto>>> getAllProduct(
            Pageable pageable){
        Page<ProductResponseDto> products = productServiceImpl.getAllProduct(pageable);

        ApiResponseDto<Page<ProductResponseDto>> response = new ApiResponseDto<>(
                true,
                "Product Retrieved Successfully",
                products
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> getProductById(
            @PathVariable @Positive Long id){
        ProductResponseDto product = productServiceImpl.getProductById(id);

        ApiResponseDto<ProductResponseDto> response = new ApiResponseDto<>(
                true,
                "Product Retrieved Successfully!",
                product
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getProductByCategoryId(
            @PathVariable @Positive Long categoryId){
        List<ProductResponseDto> products = productServiceImpl.getProductByCategoryId(categoryId);

        ApiResponseDto<List<ProductResponseDto>> response = new ApiResponseDto<>(
                true,
                "Product Retrieved Successfully!",
                products
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getProductByBrandId(
            @PathVariable @Positive Long brandId){
        List<ProductResponseDto> products = productServiceImpl.getProductByBrandId(brandId);
        ApiResponseDto<List<ProductResponseDto>> response = new ApiResponseDto<>(
                true,
                "Product Retrieved Successfully",
                products
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/series/{seriesId}")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getProductBySeriesId(
            @PathVariable @Positive Long seriesId){
        List<ProductResponseDto> products = productServiceImpl.getProductBySeriesId(seriesId);

        ApiResponseDto<List<ProductResponseDto>> response = new ApiResponseDto<>(
                true,
                "Product Retrieved Successfully",
                products
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> updateProductById(
            @PathVariable @Positive Long id,
            @RequestBody @Valid ProductRequestDto dto){
        ProductResponseDto product = productServiceImpl.updateProduct(id,dto);

        ApiResponseDto<ProductResponseDto> response = new ApiResponseDto<>(
                true,
                "Product Updated Successfully",
                product
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteProduct(
            @PathVariable @Positive Long id){
        productServiceImpl.deleteProduct(id);
        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Product Deleted Successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}
