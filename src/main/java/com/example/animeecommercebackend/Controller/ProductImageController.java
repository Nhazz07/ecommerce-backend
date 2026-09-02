package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.ProductImageRequestDto;
import com.example.animeecommercebackend.Dto.Response.ProductImageResponseDto;
import com.example.animeecommercebackend.Service.Impl.ProductImageServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productImage")
public class ProductImageController {

    private final ProductImageServiceImpl productImageServiceImpl;

    public ProductImageController(ProductImageServiceImpl productImageServiceImpl) {
        this.productImageServiceImpl = productImageServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<ProductImageResponseDto>> createProduct(@RequestBody @Valid ProductImageRequestDto dto){
        ProductImageResponseDto productImage = productImageServiceImpl.createProductImage(dto);

        ApiResponseDto<ProductImageResponseDto> response = new ApiResponseDto<>(
                true,
                "Product Image Created Successfully",
                productImage
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductImageResponseDto>> getProductImageById(@PathVariable @Positive Long id){
        ProductImageResponseDto productImage = productImageServiceImpl.getProductImageById(id);

        ApiResponseDto<ProductImageResponseDto> response = new ApiResponseDto<>(
                true,
                "Product Image Retrieved Successfully",
                productImage
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/productId/{productId}")
    public ResponseEntity<ApiResponseDto<List<ProductImageResponseDto>>> getProductImageByProductId(@PathVariable @Positive Long productId){
        List<ProductImageResponseDto> productImage = productImageServiceImpl.getImageByProductId(productId);

        ApiResponseDto<List<ProductImageResponseDto>> response = new ApiResponseDto<>(
                true,
                "Product Image Updated Successfully",
                productImage
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ProductImageResponseDto>>> getAllProductImage(){
        List<ProductImageResponseDto> productImages = productImageServiceImpl.getAllProductImages();

        ApiResponseDto<List<ProductImageResponseDto>> response = new ApiResponseDto<>(
                true,
                "Product Images Retrieved Successfully",
                productImages
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductImageResponseDto>> updateProductImage(@PathVariable @Positive Long id, @RequestBody  @Valid ProductImageRequestDto dto){
        ProductImageResponseDto productImage = productImageServiceImpl.updateProductImage(id,dto);

        ApiResponseDto<ProductImageResponseDto> response = new ApiResponseDto<>(
                true,
                "Product Image Updated Successfully",
                productImage
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteProductImage(@PathVariable @Positive Long id){
        productImageServiceImpl.deleteProductImage(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Product Image Delete Successfully!",
                null
        );
        return ResponseEntity.ok(response);
    }
}
