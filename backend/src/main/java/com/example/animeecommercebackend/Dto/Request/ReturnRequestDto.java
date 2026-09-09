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
public class ReturnRequestDto {

    @NotBlank(message = "Return reason is required")
    private String reason;

    @NotNull(message = "Order ID is required")
    private Long orderId;
}