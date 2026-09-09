package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Response.CloudinaryUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    CloudinaryUploadResponseDto uploadImage(MultipartFile file);

    void deleteImage(String publicId);
}