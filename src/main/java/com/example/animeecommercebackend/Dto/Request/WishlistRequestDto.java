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
public class WishlistRequestDto {

    @NotBlank(message = "Wishlist name is required")
    private String name;

    @NotBlank(message = "Wishlist description is required")
    private String description;

    @NotNull(message = "Public status is required")
    private Boolean isPublic;

    @NotEmpty(message = "At least one product variant is required")
    private List<Long> productIds;
}