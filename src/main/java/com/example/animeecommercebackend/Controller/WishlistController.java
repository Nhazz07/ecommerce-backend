package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.WishlistRequestDto;
import com.example.animeecommercebackend.Dto.Response.WishlistResponseDto;
import com.example.animeecommercebackend.Service.Impl.WishlistServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistServiceImpl wishlistServiceImpl;

    public WishlistController(WishlistServiceImpl wishlistServiceImpl) {
        this.wishlistServiceImpl = wishlistServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<WishlistResponseDto>> createWishlist(
            @RequestBody @Valid WishlistRequestDto dto) {

        WishlistResponseDto wishlist =
                wishlistServiceImpl.createWishlist(dto);

        ApiResponseDto<WishlistResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Wishlist Created Successfully",
                        wishlist
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<WishlistResponseDto>> getWishlistById(
            @PathVariable @Positive Long id) {

        WishlistResponseDto wishlist =
                wishlistServiceImpl.getWIshListById(id);

        ApiResponseDto<WishlistResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Wishlist Retrieved Successfully",
                        wishlist
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponseDto<List<WishlistResponseDto>>> getWishlistByUserId(
            @PathVariable @Positive Long userId) {

        List<WishlistResponseDto> wishlists =
                wishlistServiceImpl.getWishlistByUserId(userId);

        ApiResponseDto<List<WishlistResponseDto>> response =
                new ApiResponseDto<>(
                        true,
                        "Wishlist Retrieved Successfully",
                        wishlists
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<WishlistResponseDto>>> getAllWishlist() {

        List<WishlistResponseDto> wishlists =
                wishlistServiceImpl.getAllWishlist();

        ApiResponseDto<List<WishlistResponseDto>> response =
                new ApiResponseDto<>(
                        true,
                        "Wishlist Retrieved Successfully",
                        wishlists
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<WishlistResponseDto>> updateWishlist(
            @PathVariable @Positive Long id,
            @RequestBody @Valid WishlistRequestDto dto) {

        WishlistResponseDto wishlist =
                wishlistServiceImpl.updateWishlist(id, dto);

        ApiResponseDto<WishlistResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Wishlist Updated Successfully",
                        wishlist
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteWishlist(
            @PathVariable @Positive Long id) {

        wishlistServiceImpl.deleteWishlist(id);

        ApiResponseDto<Void> response =
                new ApiResponseDto<>(
                        true,
                        "Wishlist Deleted Successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{wishlistId}/products/{productId}")
    public WishlistResponseDto addProduct(
            @PathVariable @Positive Long wishlistId,
            @PathVariable @Positive Long productId) {

        return wishlistServiceImpl.addProduct(
                wishlistId,
                productId
        );
    }

    @DeleteMapping("/{wishlistId}/products/{productId}")
    public WishlistResponseDto removeProduct(
            @PathVariable @Positive Long wishlistId,
            @PathVariable @Positive Long productId) {

        return wishlistServiceImpl.removeProduct(
                wishlistId,
                productId
        );
    }
}