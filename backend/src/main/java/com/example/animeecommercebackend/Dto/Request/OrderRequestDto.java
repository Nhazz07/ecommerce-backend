package com.example.animeecommercebackend.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "At least one product variant is required")
    private List<Long> productVariantIds;

    private Long couponId;

    @NotNull(message = "Payment ID is required")
    private Long paymentId;

    @NotNull(message = "Shipment ID is required")
    private Long shipmentId;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;
}