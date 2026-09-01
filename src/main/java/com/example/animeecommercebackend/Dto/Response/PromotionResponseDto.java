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
public class PromotionResponseDto {

    private Long id;

    private String name;
    private String description;
    private String discountTypes;
    private BigDecimal discountValue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private List<Long> productIds;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
