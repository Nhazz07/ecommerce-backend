package com.example.animeecommercebackend.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponseDto {

    private Long id;
    private String name;
    private String description;
    private String logoUrl;

    private List<Long> productId;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
