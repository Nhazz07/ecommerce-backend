package com.example.animeecommercebackend.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

    @NotBlank
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    private String name;
    private String description;
    private String imageUrl;

}
