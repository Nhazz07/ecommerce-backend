package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.ReturnRequestDto;
import com.example.animeecommercebackend.Dto.Response.ReturnResponseDto;
import com.example.animeecommercebackend.Service.Impl.ReturnServiceImpl;
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
@RequestMapping("/api/return")
@Validated
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnServiceImpl returnServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<ReturnResponseDto>> createReturn(
            @RequestBody @Valid ReturnRequestDto dto){

        ReturnResponseDto AReturn = returnServiceImpl.createReturn(dto);

        ApiResponseDto<ReturnResponseDto> response = new ApiResponseDto<>(
                true,
                "Return Created Successfully",
                AReturn
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<ReturnResponseDto>> getReturnById(
            @PathVariable @Positive Long id){

        ReturnResponseDto AReturn = returnServiceImpl.getReturnById(id);

        ApiResponseDto<ReturnResponseDto> response = new ApiResponseDto<>(
                true,
                "Return Retrieved Successfully",
                AReturn
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/orderId/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<List<ReturnResponseDto>>> getReturnByOrderId(
            @PathVariable @Positive Long orderId){

        List<ReturnResponseDto> returns =
                returnServiceImpl.getReturnByOrderId(orderId);

        ApiResponseDto<List<ReturnResponseDto>> response = new ApiResponseDto<>(
                true,
                "Return Retrieved Successfully",
                returns
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<List<ReturnResponseDto>>> getAllReturn(){

        List<ReturnResponseDto> returns =
                returnServiceImpl.getAllReturn();

        ApiResponseDto<List<ReturnResponseDto>> response = new ApiResponseDto<>(
                true,
                "Return Retrieved Successfully",
                returns
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<ReturnResponseDto>> updateReturn(
            @PathVariable @Positive Long id,
            @RequestBody @Valid ReturnRequestDto dto){

        ReturnResponseDto AReturn =
                returnServiceImpl.updateReturn(id, dto);

        ApiResponseDto<ReturnResponseDto> response = new ApiResponseDto<>(
                true,
                "Return Updated Successfully",
                AReturn
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<Void>> deleteReturn(
            @PathVariable @Positive Long id){

        returnServiceImpl.deleteReturn(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Return Deleted Successfully",
                null
        );

        return ResponseEntity.ok(response);
    }
}