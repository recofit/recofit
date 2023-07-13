package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.MemberDao;
import site.recofit.ssafit.dao.ReviewDao;
import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.domain.Review;
import site.recofit.ssafit.dto.member.MemberFollowListResponseDto;
import site.recofit.ssafit.dto.review.ReviewListResponseDto;
import site.recofit.ssafit.dto.review.ReviewRegistRequestDto;
import site.recofit.ssafit.dto.review.ReviewUpdateRequestDto;
import site.recofit.ssafit.exception.MemberException;
import site.recofit.ssafit.exception.ReviewException;
import site.recofit.ssafit.exception.status.MemberStatus;
import site.recofit.ssafit.exception.status.ReviewStatus;
import site.recofit.ssafit.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDao reviewDao;
    private final MemberDao memberDao;

    @Transactional
    public void registReview(final ReviewRegistRequestDto requestDto) {
        final Review review = Review.builder()
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
    public List<ReviewListResponseDto> getAllReview(final String placeName) {
        final List<Review> reviewList = reviewDao.findAll(placeName);

        return convertReviewListToDtoList(reviewList);
    }

    @Override
    public List<ReviewListResponseDto> getThreeReview(final String placeName) {
        final List<Review> reviewList = reviewDao.findThree(placeName);

        return convertReviewListToDtoList(reviewList);
    }

    @Override
    public List<ReviewListResponseDto> getReviewByTitle(final String placeName, final String title) {
        final List<Review> reviewList = reviewDao.findByTitle(placeName, title);

        return convertReviewListToDtoList(reviewList);
    }

    private List<ReviewListResponseDto> convertReviewListToDtoList(final List<Review> reviewList) {
        final List<ReviewListResponseDto> dtoList = new ArrayList<>();

        for (final Review review : reviewList) {
            final ReviewListResponseDto responseDto = ReviewListResponseDto.builder()
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
    public ReviewListResponseDto getReviewById(final int reviewId) {
        final Review review = reviewDao.findById(reviewId).orElseThrow(
                () -> new ReviewException(ReviewStatus.NOT_EXISTING_REVIEW)
        );

        return ReviewListResponseDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .name(review.getName())
                .content(review.getContent())
                .picture(review.getPicture())
                .rate(review.getRate())
                .likeCnt(review.getLikeCnt())
                .lastModifiedDate(review.getLastModifiedDate())
                .build();
    }

    @Transactional
    public void updateReview(final int reviewId, final ReviewUpdateRequestDto requestDto) {
        if (reviewDao.findById(reviewId).isEmpty()) {
            throw new ReviewException(ReviewStatus.NOT_EXISTING_REVIEW);
        }

        final Review review = Review.builder()
                .id(reviewId)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .picture(requestDto.getPicture())
                .rate(requestDto.getRate())
                .build();

        reviewDao.updateReview(review);
    }

    @Transactional
    public void removeReview(final int reviewId) {
        if (reviewDao.findById(reviewId).isEmpty()) {
            throw new ReviewException(ReviewStatus.NOT_EXISTING_REVIEW);
        }

        reviewDao.removeAllReviewLike(reviewId);
        reviewDao.removeReview(reviewId);
    }

    @Transactional
    public void updateReviewLike(final int reviewId, final int memberId) {
        if (reviewDao.findById(reviewId).isEmpty()) {
            throw new ReviewException(ReviewStatus.NOT_EXISTING_REVIEW);
        }

        if (memberDao.findById(memberId).isEmpty()) {
            throw new MemberException(MemberStatus.NOT_EXISTING_MEMBER);
        }

        reviewDao.saveReviewLike(reviewId, memberId);
        reviewDao.updateReviewLike(reviewId);
    }

    @Transactional
    public void updateReviewDislike(final int reviewId, final int memberId) {
        if (reviewDao.findById(reviewId).isEmpty()) {
            throw new ReviewException(ReviewStatus.NOT_EXISTING_REVIEW);
        }

        if (memberDao.findById(memberId).isEmpty()) {
            throw new MemberException(MemberStatus.NOT_EXISTING_MEMBER);
        }

        reviewDao.removeReviewLike(reviewId, memberId);
        reviewDao.updateReviewDislike(reviewId);
    }
}