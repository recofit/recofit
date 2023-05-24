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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;

    @PostMapping("/write")
    public ResponseEntity<?> registReview(@RequestBody final ReviewRegistRequestDto requestDto) {
        service.registReview(requestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/list/{placeName}")
    public ResponseEntity<?> selectAllReview(@PathVariable final String placeName) {
        List<ReviewListResponseDto> responseDtos = service.selectAll(placeName);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/top/{placeId}")
    public ResponseEntity<?> selectThreeReview(@PathVariable final String placeName) {
        List<ReviewListResponseDto> responseDtos = service.selectThree(placeName);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}") // 리뷰 확인
    public ResponseEntity<?> selectById(@PathVariable final int reviewId) {
        ReviewListResponseDto responseDto = service.selectById(reviewId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable final int reviewId, @RequestBody final ReviewUpdateRequestDto requestDto) {
        service.updateReview(reviewId, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> removeReview(@PathVariable final int reviewId) {
        service.removeReview(reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/like/{reviewId}")
    public ResponseEntity<?> updateReviewLike(@PathVariable final int reviewId, @RequestParam final int memberId) {
        service.updateReviewLike(reviewId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/dislike/{reviewId}")
    public ResponseEntity<?> updateReviewDislike(@PathVariable final int reviewId, @RequestParam final int memberId) {
        service.updateReviewDislike(reviewId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
