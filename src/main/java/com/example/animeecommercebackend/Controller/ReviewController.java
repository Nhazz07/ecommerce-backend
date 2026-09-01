package com.example.animeecommercebackend.Controller;

import ch.qos.logback.core.sift.AppenderTracker;
import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.ReviewRequestDto;
import com.example.animeecommercebackend.Dto.Response.ReviewResponseDto;
import com.example.animeecommercebackend.Entity.Review;
import com.example.animeecommercebackend.Service.Impl.ReviewServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewServiceImpl reviewServiceImpl;

    public ReviewController(ReviewServiceImpl reviewServiceImpl) {
        this.reviewServiceImpl = reviewServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<ReviewResponseDto>> createReview(@RequestBody @Valid ReviewRequestDto dto){
        ReviewResponseDto review = reviewServiceImpl.createReview(dto);

        ApiResponseDto<ReviewResponseDto> response = new ApiResponseDto<>(
                true,
                "Review Created Successfully",
                review
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ReviewResponseDto>> getReviewById(@PathVariable @Positive Long id){
        ReviewResponseDto review = reviewServiceImpl.getReviewById(id);
        ApiResponseDto<ReviewResponseDto> response = new ApiResponseDto<>(
                true,
                "Review Retrieved Successdully",
                review
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<List<ReviewResponseDto>>> getReviewByProductId(@PathVariable @Positive Long productId){
        List<ReviewResponseDto> review = reviewServiceImpl.getReviewByProductId(productId);

        ApiResponseDto<List<ReviewResponseDto>> response = new ApiResponseDto<>(
                true,
                "Review Retrieved Successfully",
                review
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<List<ReviewResponseDto>>> getPReviewByUserId(@PathVariable @Positive Long userId){
        List<ReviewResponseDto> reviews = reviewServiceImpl.getReviewByUserId(userId);
        ApiResponseDto<List<ReviewResponseDto>> response = new ApiResponseDto<>(
                true,
                "Review Retrieved Successfully",
                reviews
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ReviewResponseDto>>> getAllReview(){
        List<ReviewResponseDto> reviews = reviewServiceImpl.getAllReview();

        ApiResponseDto<List<ReviewResponseDto>> response = new ApiResponseDto<>(
                true,
                "Review Retrieved Successfully",
                reviews
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ReviewResponseDto>> updateReview(@PathVariable @Positive Long id, @RequestBody @Valid ReviewRequestDto dto){
        ReviewResponseDto review = reviewServiceImpl.updateReview(id,dto);

        ApiResponseDto<ReviewResponseDto> response = new ApiResponseDto<>(
                true,
                "Review Updated Successfully",
                review
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteReview(@PathVariable @Positive Long id){
        reviewServiceImpl.deleteReview(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Review Deleted Successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}
