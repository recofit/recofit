package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.review.ReviewListResponseDto;
import site.recofit.ssafit.dto.review.ReviewRegistRequestDto;
import site.recofit.ssafit.dto.review.ReviewUpdateRequestDto;

import java.util.List;

public interface ReviewService {
    // 리뷰 등록
    void registReview(final ReviewRegistRequestDto requestDto);

    // 리뷰 목록 확인
    List<ReviewListResponseDto> selectAll(final String placeName);

    // 리뷰 추천 top 3 확인
    List<ReviewListResponseDto> selectThree(final String placeName);

    // 리뷰 확인
    ReviewListResponseDto selectById(final int reviewId);
    
    // 리뷰 검색
    List<ReviewListResponseDto> selectByTitle(final String placeName, final String title);

    // 리뷰 수정
    void updateReview(final int reviewId, final ReviewUpdateRequestDto requestDto);

    // 리뷰 삭제
    void removeReview(final int reviewId);

    // 리뷰 추천
    void updateReviewLike(final int reviewId, final int memberId);

    // 리뷰 추천 해제
    void updateReviewDislike(final int reviewId, final int memberId);
}
