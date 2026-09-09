package com.example.animeecommercebackend.Dto.Response;

import com.example.animeecommercebackend.Entity.Enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentResponseDto {

    private Long id;
    private String trackingNumber;
    private String carrier;
    private String shippingMethod;
    private ShipmentStatus status;

    private LocalDateTime shippedAt;
    private LocalDateTime estimatedDeliveryDate;
    private LocalDateTime deliveredAt;

    private Long orderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
