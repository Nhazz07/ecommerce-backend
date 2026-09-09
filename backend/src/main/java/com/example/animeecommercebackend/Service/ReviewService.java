package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.ReviewRequestDto;
import com.example.animeecommercebackend.Dto.Response.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto createReview(ReviewRequestDto dto);
    ReviewResponseDto getReviewById(Long id);
    List<ReviewResponseDto> getAllReview();
    List<ReviewResponseDto> getReviewByProductId(Long productId);
    List<ReviewResponseDto> getReviewByUserId(Long userId);
    ReviewResponseDto updateReview(Long id, ReviewRequestDto dto);
    void deleteReview(Long id);
}
