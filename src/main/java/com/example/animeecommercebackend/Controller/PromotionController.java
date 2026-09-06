package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.PromotionRequestDto;
import com.example.animeecommercebackend.Dto.Response.PromotionResponseDto;
import com.example.animeecommercebackend.Entity.Promotion;
import com.example.animeecommercebackend.Service.Impl.PromotionServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotion")
@Validated
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionServiceImpl promotionServiceImpl;


    @PostMapping
    public ResponseEntity<ApiResponseDto<PromotionResponseDto>> createPromotion(
            @RequestBody @Valid PromotionRequestDto dto){
        PromotionResponseDto promotion = promotionServiceImpl.createPromotion(dto);

        ApiResponseDto<PromotionResponseDto> response = new ApiResponseDto<>(
                true,
                "Promotion Created Successfully",
                promotion
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<PromotionResponseDto>> getPromotionById(
            @PathVariable @Positive Long id){
        PromotionResponseDto promotion = promotionServiceImpl.getPromotionById(id);

        ApiResponseDto<PromotionResponseDto> response = new ApiResponseDto<>(
                true,
                "Promotion Retrieved Successfully",
                promotion
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/productId/{productId}")
    public ResponseEntity<ApiResponseDto<PromotionResponseDto>> getPromotionByProductId(
            @PathVariable @Positive Long productId){
        PromotionResponseDto promotion = promotionServiceImpl.getPromotionByProductId(productId);

        ApiResponseDto<PromotionResponseDto> response = new ApiResponseDto<>(
                true,
                "Promotion Retrieved Successfully",
                promotion
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<PromotionResponseDto>>> getAllPromotion(){
        List<PromotionResponseDto> promotions = promotionServiceImpl.getAllPromotion();

        ApiResponseDto<List<PromotionResponseDto>> response = new ApiResponseDto<>(
                true,
                "Promotion Retrieved Successfully",
                promotions
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<PromotionResponseDto>> updatePromotion(
            @PathVariable @Positive Long id,
            @RequestBody @Valid PromotionRequestDto dto){
        PromotionResponseDto promotion = promotionServiceImpl.updatePromotion(id,dto);

        ApiResponseDto<PromotionResponseDto> response = new ApiResponseDto<>(
                true,
                "Promotion Updated Successfully",
                promotion
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deletePromotion(
            @PathVariable @Positive Long id){
        promotionServiceImpl.deletePromotion(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Promotion Deleted Successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}
