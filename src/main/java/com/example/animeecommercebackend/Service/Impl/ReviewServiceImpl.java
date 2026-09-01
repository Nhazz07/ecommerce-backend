package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.ReviewRequestDto;
import com.example.animeecommercebackend.Dto.Response.ReviewResponseDto;
import com.example.animeecommercebackend.Entity.Promotion;
import com.example.animeecommercebackend.Entity.Review;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.ReviewMapper;
import com.example.animeecommercebackend.Repository.ReviewRepository;
import com.example.animeecommercebackend.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    @Override
    public ReviewResponseDto createReview(ReviewRequestDto dto) {
        Review review = ReviewMapper.toEntity(dto);

        Review saved = reviewRepository.save(review);
        return ReviewMapper.toResponse(saved);
    }

    @Override
    public ReviewResponseDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review Not Found"));
        return ReviewMapper.toResponse(review);
    }

    @Override
    public List<ReviewResponseDto> getAllReview() {
        return reviewRepository.findAll().stream().map(ReviewMapper::toResponse).toList();
    }

    @Override
    public List<ReviewResponseDto> getReviewByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        if(reviews.isEmpty()){
            throw new ResourceNotFoundException("Reviews from Product is Not Found");
        }
        return reviews.stream().map(ReviewMapper::toResponse).toList();
    }

    @Override
    public List<ReviewResponseDto> getReviewByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        if(reviews.isEmpty()){
            throw new ResourceNotFoundException("Reviews from User is Not Found");
        }
        return reviews.stream().map(ReviewMapper::toResponse).toList();
    }

    @Override
    public ReviewResponseDto updateReview(Long id, ReviewRequestDto dto) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review Not Found"));

        review.setComment(dto.getComment());
        review.setRating(dto.getRating());

        Review saved = reviewRepository.save(review);
        return ReviewMapper.toResponse(saved);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review Not Found"));

        reviewRepository.delete(review);
    }
}
