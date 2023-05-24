package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.ReviewDao;
import site.recofit.ssafit.domain.Review;
import site.recofit.ssafit.dto.review.ReviewListResponseDto;
import site.recofit.ssafit.dto.review.ReviewRegistRequestDto;
import site.recofit.ssafit.dto.review.ReviewUpdateRequestDto;
import site.recofit.ssafit.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDao reviewDao;

    @Transactional
    public void registReview(ReviewRegistRequestDto requestDto) {

        Review review = Review.builder()
                .memberId(requestDto.getMemberId())
                .placeId(requestDto.getPlaceId())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .picture(requestDto.getPicture())
                .rate(requestDto.getRate())
                .build();

        reviewDao.saveReview(review);
    }

    @Override
    public List<ReviewListResponseDto> selectAll(String placeName) {
        List<Review> reviewList = reviewDao.findAll(placeName);
        List<ReviewListResponseDto> dtoList = new ArrayList<>();

        for (Review review : reviewList) {
            ReviewListResponseDto responseDto = ReviewListResponseDto.builder()
                    .id(review.getId())
                    .title(review.getTitle())
                    .name(review.getName())
                    .content(review.getContent())
                    .picture(review.getPicture())
                    .rate(review.getRate())
                    .likeCnt(review.getLikeCnt())
                    .lastModifiedDate(review.getLastModifiedDate())
                    .build();

            dtoList.add(responseDto);
        }

        return dtoList;
    }

    @Override
    public List<ReviewListResponseDto> selectThree(String placeId) {
        List<Review> reviewList = reviewDao.findThree(placeId);
        List<ReviewListResponseDto> dtoList = new ArrayList<>();

        for (Review review : reviewList) {
            ReviewListResponseDto responseDto = ReviewListResponseDto.builder()
                    .id(review.getId())
                    .title(review.getTitle())
                    .content(review.getContent())
                    .picture(review.getPicture())
                    .rate(review.getRate())
                    .likeCnt(review.getLikeCnt())
                    .lastModifiedDate(review.getLastModifiedDate())
                    .build();

            dtoList.add(responseDto);
        }

        return dtoList;
    }

    @Override
    public ReviewListResponseDto selectById(int reviewId) {
        Review review = reviewDao.findById(reviewId);

        ReviewListResponseDto responseDto = ReviewListResponseDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .picture(review.getPicture())
                .rate(review.getRate())
                .likeCnt(review.getLikeCnt())
                .lastModifiedDate(review.getLastModifiedDate())
                .build();

        return responseDto;
    }

    @Transactional
    public void updateReview(int reviewId, ReviewUpdateRequestDto requestDto) {
        Review review = Review.builder()
                .id(reviewId)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .picture(requestDto.getPicture())
                .rate(requestDto.getRate())
                .build();

        reviewDao.updateReview(review);
    }

    @Transactional
    public void removeReview(int reviewId) {
        reviewDao.removeAllReviewLike(reviewId);
        reviewDao.removeReview(reviewId);
    }

    @Transactional
    public void updateReviewLike(int reviewId, int memberId) {
        reviewDao.saveReviewLike(reviewId, memberId);
        reviewDao.updateReviewLike(reviewId);
    }

    @Transactional
    public void updateReviewDislike(int reviewId, int memberId) {
        reviewDao.removeReviewLike(reviewId, memberId);
        reviewDao.updateReviewDislike(reviewId);
    }
}