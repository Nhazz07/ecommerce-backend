package com.example.animeecommercebackend.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CloudinaryUploadResponseDto {

    private String imageUrl;
    private String publicId;
}