package com.example.animeecommercebackend.Dto.Request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class CouponRequestDto {
    @NotBlank(message = "Coupon code is required")
    private String code;

    @NotBlank(message = "Coupon description is required")
    private String description;
    @NotBlank(message = "Discount type is required")
    private String discountType;

    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.01", message = "Discount value must be greater than 0")
    private BigDecimal discountValue;

    @NotNull(message = "Minimum order discount is required")
    @DecimalMin(value = "0.0", message = "Minimum order cannot be negative")
    private BigDecimal minimumOrderAmount;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    private LocalDateTime endDate;

    @NotNull(message = "Usage limit is required")
    @Min(value = 1, message = "Usage limit must be at least 1" )
    private Integer usageLimit;

    @NotNull(message = "Active status is required")
    private Boolean active;
//    private List<Long> OrderId;


}
