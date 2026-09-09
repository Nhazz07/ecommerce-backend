package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.AddressRequestDto;
import com.example.animeecommercebackend.Dto.Response.AddressResponseDto;
import com.example.animeecommercebackend.Service.Impl.AddressServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@SecurityRequirement(name = "bearerAuth")
public class AddressController {

    private final AddressServiceImpl addressServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponseDto<AddressResponseDto>> createAddress(
            @RequestBody @Valid AddressRequestDto dto) {

        AddressResponseDto address =
                addressServiceImpl.createdAddress(dto);

        ApiResponseDto<AddressResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Address Created Successfully",
                        address
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponseDto<AddressResponseDto>> getAddressById(
            @PathVariable @Positive Long id) {

        AddressResponseDto address =
                addressServiceImpl.getAddressById(id);

        ApiResponseDto<AddressResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Address Retrieved Successfully",
                        address
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponseDto<List<AddressResponseDto>>> getAllAddress() {

        List<AddressResponseDto> address =
                addressServiceImpl.getAllAddress();

        ApiResponseDto<List<AddressResponseDto>> response =
                new ApiResponseDto<>(
                        true,
                        "Address Retrieved Successfully!",
                        address
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponseDto<AddressResponseDto>> updateAddress(
            @PathVariable @Positive Long id,
            @RequestBody @Valid AddressRequestDto dto) {

        AddressResponseDto address =
                addressServiceImpl.updateAddress(id, dto);

        ApiResponseDto<AddressResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Address Updated Successfully",
                        address
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponseDto<Void>> deleteAddress(
            @PathVariable @Positive Long id) {

        addressServiceImpl.deleteAddress(id);

        ApiResponseDto<Void> response =
                new ApiResponseDto<>(
                        true,
                        "Address Deleted Successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{addressId}/default/{userId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponseDto<AddressResponseDto>> setDefaultAddress(
            @PathVariable @Positive Long addressId,
            @PathVariable @Positive Long userId) {

        AddressResponseDto address =
                addressServiceImpl.setDefaultAddress(
                        addressId,
                        userId
                );

        ApiResponseDto<AddressResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "Default Address Set Successfully",
                        address
                );

        return ResponseEntity.ok(response);
    }
}