package com.example.animeecommercebackend.Dto.Response;

import com.example.animeecommercebackend.Entity.Enums.ReturnStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnResponseDto {

    private Long id;
    private String returnNumber;
    private String reason;
    private ReturnStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime processedAt;
    private Long orderId;
    private Long refundId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
