package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.review.ReviewListResponseDto;
import site.recofit.ssafit.dto.review.ReviewRegistRequestDto;
import site.recofit.ssafit.dto.review.ReviewUpdateRequestDto;
import site.recofit.ssafit.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/write")
    public ResponseEntity<Void> registReview(@RequestBody final ReviewRegistRequestDto requestDto) {
        reviewService.registReview(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list/{placeName}")
    public ResponseEntity<List<ReviewListResponseDto>> getAllReview(@PathVariable final String placeName) {
        final List<ReviewListResponseDto> list = reviewService.getAllReview(placeName);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/top/{placeName}")
    public ResponseEntity<List<ReviewListResponseDto>> getThreeReview(@PathVariable final String placeName) {
        final List<ReviewListResponseDto> list = reviewService.getThreeReview(placeName);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/search/{placeName}")
    public ResponseEntity<List<ReviewListResponseDto>> getReviewByTitle(@PathVariable final String placeName, @RequestParam final String title) {
        final List<ReviewListResponseDto> list = reviewService.getReviewByTitle(placeName, title);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{reviewId}") // 리뷰 확인
    public ResponseEntity<ReviewListResponseDto> getReviewById(@PathVariable final int reviewId) {
        final ReviewListResponseDto responseDto = reviewService.getReviewById(reviewId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateReview(@PathVariable final int reviewId, @RequestBody final ReviewUpdateRequestDto requestDto) {
        reviewService.updateReview(reviewId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> removeReview(@PathVariable final int reviewId) {
        reviewService.removeReview(reviewId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/like/{reviewId}")
    public ResponseEntity<Void> updateReviewLike(@PathVariable final int reviewId, @RequestParam final int memberId) {
        reviewService.updateReviewLike(reviewId, memberId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/dislike/{reviewId}")
    public ResponseEntity<Void> updateReviewDislike(@PathVariable final int reviewId, @RequestParam final int memberId) {
        reviewService.updateReviewDislike(reviewId, memberId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
