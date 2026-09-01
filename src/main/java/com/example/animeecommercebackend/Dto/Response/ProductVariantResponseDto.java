package com.example.animeecommercebackend.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantResponseDto {

    private Long id;
    private String sku;
    private String name;
    private BigDecimal price;
    private String size;
    private String color;

    private Long productId;
    private String productName;

    private Long inventoryId;

    private List<Long> orderId;

    private List<Long> cartId;

    private List<Long> wishlistId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


