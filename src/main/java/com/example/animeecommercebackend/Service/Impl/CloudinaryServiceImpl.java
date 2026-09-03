package com.example.animeecommercebackend.Service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.animeecommercebackend.Dto.Response.CloudinaryUploadResponseDto;
import com.example.animeecommercebackend.Service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public CloudinaryUploadResponseDto uploadImage(
            MultipartFile file
    ) {

        try {

            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "image"
                    )
            );

            String imageUrl =
                    result.get("secure_url").toString();

            String publicId =
                    result.get("public_id").toString();

            return new CloudinaryUploadResponseDto(
                    imageUrl,
                    publicId
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to upload image to Cloudinary",
                    e
            );
        }
    }

    @Override
    public void deleteImage(String publicId) {

        try {

            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.emptyMap()
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to delete image from Cloudinary",
                    e
            );
        }
    }
}