package com.example.animeecommercebackend.Entity;

import com.example.animeecommercebackend.Entity.Enums.ShipmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tracking number is required")
    @Column(nullable = false, unique = true)
    private String trackingNumber;

    @NotBlank(message = "Carrier is required")
    private String carrier;

    @NotBlank(message = "Shipment method is required")
    private String shipmentMethod;

    @NotNull(message = "Shipment status is required")
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    private LocalDateTime shippedAt;

    @NotNull(message = "Estimated Delivery date is required")
    private LocalDateTime estimatedDeliveryDate;

    private LocalDateTime deliveryAt;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
