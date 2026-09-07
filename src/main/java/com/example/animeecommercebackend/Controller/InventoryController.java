package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.InventoryRequestDto;
import com.example.animeecommercebackend.Dto.Response.InventoryResponseDto;
import com.example.animeecommercebackend.Service.Impl.InventoryServiceImpl;
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
@RequestMapping("/api/inventory")
@Validated
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<InventoryResponseDto>> createInventory(
            @RequestBody @Valid InventoryRequestDto dto){
        InventoryResponseDto inventory = inventoryServiceImpl.createInventory(dto);

        ApiResponseDto<InventoryResponseDto> response = new ApiResponseDto<>(
                true,
                "Inventory Created Successfully",
                inventory
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<InventoryResponseDto>> getInventoryById(
            @PathVariable @Positive Long id){
        InventoryResponseDto inventory = inventoryServiceImpl.getInventoryById(id);

        ApiResponseDto<InventoryResponseDto> response = new ApiResponseDto<>(
                true,
                "Inventory Retrieved Successfully!!",
                inventory
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<List<InventoryResponseDto>>> getAllInventory(){
        List<InventoryResponseDto> inventories = inventoryServiceImpl.getAllInventory();

        ApiResponseDto<List<InventoryResponseDto>> response = new ApiResponseDto<>(
                true,
                "Inventory Retrieved Successfully!!",
                inventories
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/product-variant/{productVariantId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<InventoryResponseDto>> getInventoryByProductVariantId(
            @PathVariable @Positive Long productVariantId){
        InventoryResponseDto inventory = inventoryServiceImpl.getInventoryByProductVariantId(productVariantId);

        ApiResponseDto<InventoryResponseDto> response = new ApiResponseDto<>(
                true,
                "Inventory Retrieved Successfully",
                inventory
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<InventoryResponseDto>> updateInventory(
            @PathVariable @Positive Long id,
            @RequestBody @Valid InventoryRequestDto dto){
        InventoryResponseDto inventory = inventoryServiceImpl.updateInventory(id,dto);

        ApiResponseDto<InventoryResponseDto> response = new ApiResponseDto<>(
                true,
                "Inventory Updated Successfully",
                inventory
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<Void>> deleteInventory(
            @PathVariable @Positive Long id){
        inventoryServiceImpl.deleteInventory(id);
        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Inventory Deleted Successfully!!",
                null
        );
        return ResponseEntity.ok(response);
    }
}
