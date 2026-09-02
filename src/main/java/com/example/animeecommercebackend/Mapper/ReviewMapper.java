package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.ReviewRequestDto;
import com.example.animeecommercebackend.Dto.Response.ReviewResponseDto;
import com.example.animeecommercebackend.Entity.Product;
import com.example.animeecommercebackend.Entity.Review;
import com.example.animeecommercebackend.Entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewMapper {

    public static Review toEntity(ReviewRequestDto dto
                           ){
        Review review = new Review();

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());


        return review;
    }

    public static ReviewResponseDto toResponse(Review review){

        ReviewResponseDto dto = new ReviewResponseDto();

        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setUpdatedAt(review.getUpdatedAt());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setUpdatedAt(review.getUpdatedAt());
        if(review.getUser() != null){
            dto.setUserId(review.getUser().getId());
            dto.setUserName(review.getUser().getUsername());
        }
        if(review.getProduct() != null){
            dto.setProductId(review.getProduct().getId());
            dto.setProductName(review.getProduct().getProductName());
        }
        return dto;
    }
}
