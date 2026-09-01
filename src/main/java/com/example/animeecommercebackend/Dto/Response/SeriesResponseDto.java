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
public class SeriesResponseDto {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;

    private List<Long> productIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
