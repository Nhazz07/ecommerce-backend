package com.example.animeecommercebackend.Entity;

import com.example.animeecommercebackend.Entity.Enums.RefundStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refunds")
public class Refund {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Refund number is required")
    @Column(nullable = false, unique = true)
    private String refundNumber;

    @NotNull(message = "Refund amount is required")
    @DecimalMin(value = "0.01", message = "Refund amount must be greater tha 0")
    private BigDecimal amount;

    @NotBlank(message = "Refund reason is required")
    private String reason;

    @NotNull(message = "Refund status is required")
    @Enumerated(EnumType.STRING)
    private RefundStatus status;

    private LocalDateTime refundAt;


    @OneToOne(mappedBy = "refund")
    private Return aReturn;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
