package com.example.animeecommercebackend.Dto.Request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GiftCardRequestDto {

    @NotBlank(message = "Gift card code is required")
    private String code;

    @NotNull(message = "Initial balance is required")
    @DecimalMin(value = "0.0", message = "Initial balance cannot negative")
    private BigDecimal initialBalance;

    @NotNull(message = "Expiration date is required")
    private LocalDateTime expirationDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "User Id is required")
    private Long userId;


}
