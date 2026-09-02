package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.GiftCardRequestDto;
import com.example.animeecommercebackend.Dto.Response.GiftCardResponseDto;
import com.example.animeecommercebackend.Service.Impl.GiftCardServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/giftCard")
public class GiftCardController {

    private final GiftCardServiceImpl giftCardServiceImpl;

    public GiftCardController(GiftCardServiceImpl giftCardServiceImpl) {
        this.giftCardServiceImpl = giftCardServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<GiftCardResponseDto>> createGiftCard(@RequestBody @Valid GiftCardRequestDto dto){
        GiftCardResponseDto giftCard = giftCardServiceImpl.createGiftCard(dto);

        ApiResponseDto<GiftCardResponseDto> response = new ApiResponseDto<>(
                true,
                "GiftCard Created SSuccessfully",
                giftCard
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<GiftCardResponseDto>> getGiftCard(@PathVariable @Positive Long id){
        GiftCardResponseDto giftCard = giftCardServiceImpl.getGiftCardById(id);

        ApiResponseDto<GiftCardResponseDto> response = new ApiResponseDto<>(
                true,
                "GiftCard Retrieved Successfully!!",
                giftCard
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<GiftCardResponseDto>>> getAllGiftCard(){
        List<GiftCardResponseDto> giftCard = giftCardServiceImpl.getAllGiftCard();

        ApiResponseDto<List<GiftCardResponseDto>> response = new ApiResponseDto<>(
                true,
                "GiftCard Retrieved Successfully",
                giftCard
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponseDto<List<GiftCardResponseDto>>> getGiftCardByUser(@PathVariable @Positive Long userId){
        List<GiftCardResponseDto> giftCards = giftCardServiceImpl.getGiftCardsByUserId(userId);

        ApiResponseDto<List<GiftCardResponseDto>> response = new ApiResponseDto<>(
                true,
                "GiftCard Retrieved SuccessFully",
                giftCards
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<GiftCardResponseDto>> updateGiftCard(@PathVariable @Positive Long id, @RequestBody @Valid GiftCardRequestDto dto){
        GiftCardResponseDto giftCard = giftCardServiceImpl.updateGiftCard(id,dto);

        ApiResponseDto<GiftCardResponseDto> response = new ApiResponseDto<>(
                true,
                "GiftCard Updated Successfully!!",
                giftCard
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteGiftCard(@PathVariable @Positive Long id){
        giftCardServiceImpl.deleteGiftCard(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "GiftCard Deleted Successfully!",
                null
        );
        return ResponseEntity.ok(response);
    }
}
