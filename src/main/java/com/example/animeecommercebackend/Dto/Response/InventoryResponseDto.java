package com.example.animeecommercebackend.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDto {

    private Long id;
    private Integer quantity;
    private Integer reservedQuantity;
    private Integer reorderLevel;
    private Integer availableQuantity;
    private Long productVariantId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
