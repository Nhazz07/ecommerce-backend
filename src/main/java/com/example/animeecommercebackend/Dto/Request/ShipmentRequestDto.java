package com.example.animeecommercebackend.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequestDto {

    @NotBlank(message = "Carrier is required")
    private String carrier;

    @NotBlank(message = "Shipping method is required")
    private String shippingMethod;

    @NotNull(message = "Order ID is required")
    private Long orderId;
}