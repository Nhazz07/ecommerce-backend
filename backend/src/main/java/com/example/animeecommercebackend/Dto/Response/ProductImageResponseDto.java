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
public class ProductImageResponseDto {

    private Long id;
    private String imageUrl;
    private String altText;
    private Integer displayOrder;
    private Long productId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
