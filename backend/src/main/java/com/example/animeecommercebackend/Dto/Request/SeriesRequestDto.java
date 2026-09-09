package com.example.animeecommercebackend.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SeriesRequestDto {

    @NotBlank(message = "Series name is required")
    private String name;

    @NotBlank(message = "Series description is required")
    private String description;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;
}