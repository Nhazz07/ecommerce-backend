package com.example.animeecommercebackend.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {

    private Long id;

    private Integer quantity;

    private Long productVariantId;

    private Long productId;

    private String productName;

    private BigDecimal price;

    private BigDecimal subtotal;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}