package site.recofit.ssafit.dao;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.Review;

import java.util.List;

@Repository
public interface ReviewDao {
    // 리뷰 등록
    void saveReview(final Review review);

    // 리뷰 목록 확인
    List<Review> findAll(final String placeName);

    // 리뷰 추천 top 3 확인
    List<Review> findThree(final String placeName);

    // 리뷰 확인
    Review findById(final int reviewId);

    // 리뷰 검색
    List<Review> findByTitle(final String placeName, final String title);

    // 리뷰 수정
    void updateReview(final Review review);

    // 리뷰 삭제
    void removeReview(final int reviewId);

    // 리뷰 삭제 시 해당 추천 정보 모두 삭제
    void removeAllReviewLike(final int reviewId);

    // 리뷰 추천 등록
    void saveReviewLike(final int reviewId, final int memberId);
    
    // 리뷰 추천 취소
    void removeReviewLike(final int reviewId, final int memberId);
    
    // 리뷰 추천수 +1
    void updateReviewLike(final int reviewId);
    
    // 리뷰 추천수 -1
    void updateReviewDislike(final int reviewId);
}
