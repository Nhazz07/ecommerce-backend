package com.example.animeecommercebackend.Dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageRequestDto {

    @NotBlank(message = "Image URL is required")
    private String urlImages;

    @NotBlank(message = "Alt text is required")
    private String altText;

    @NotNull(message = "Display order is required")
    @Min(value = 0, message = "Display order cannot be negative")
    private Integer displayOrder;

    @NotNull(message = "Product ID is required")
    private Long productId;
}