package com.example.animeecommercebackend.Dto.Request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class PromotionRequestDto {

    @NotBlank(message = "Promotion name is required")
    private String name;

    @NotBlank(message = "Promotion description is required")
    private String description;

    @NotBlank(message = "Discount type is required")
    private String discountTypes;

    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.01", message = "Discount value must be greater than 0")
    private BigDecimal discountValue;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    private LocalDateTime endDate;

    @NotNull(message = "Active status is required")
    private Boolean active;

    @NotEmpty(message = "At least one product is required")
    private List<Long> productIds;
}