package com.example.animeecommercebackend.Dto.Response;

import com.example.animeecommercebackend.Entity.Enums.RefundStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefundResponseDto {

    private Long id;
    private String refundNumber;
    private BigDecimal amount;
    private String reason;
    private RefundStatus status;
    private LocalDateTime refundAt;
    private Long returnId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
