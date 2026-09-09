package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.WishlistRequestDto;
import com.example.animeecommercebackend.Dto.Response.WishlistResponseDto;
import com.example.animeecommercebackend.Service.CurrentUserService;
import com.example.animeecommercebackend.Service.Impl.WishlistServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@Validated
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistServiceImpl wishlistServiceImpl;
    private final CurrentUserService currentUserService;


    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
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
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @SecurityRequirement(name = "bearerAuth")

    public ResponseEntity<ApiResponseDto<WishlistResponseDto>> getWishlistById(
            @PathVariable @Positive Long id) {

        WishlistResponseDto wishlist =
                wishlistServiceImpl.getWIshListById(id);
        System.out.println("GET WISHLIST CONTROLLER REACHED");
        ApiResponseDto<WishlistResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Wishlist Retrieved Successfully",
                        wishlist
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<List<WishlistResponseDto>>> getMyWishlist() {
        Long userId = currentUserService.getCurrentUser().getId();
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
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
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
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
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
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
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
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public WishlistResponseDto addProduct(
            @PathVariable @Positive Long wishlistId,
            @PathVariable @Positive Long productId) {

        return wishlistServiceImpl.addProduct(
                wishlistId,
                productId
        );
    }

    @DeleteMapping("/{wishlistId}/products/{productId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public WishlistResponseDto removeProduct(
            @PathVariable @Positive Long wishlistId,
            @PathVariable @Positive Long productId) {

        return wishlistServiceImpl.removeProduct(
                wishlistId,
                productId
        );
    }
}