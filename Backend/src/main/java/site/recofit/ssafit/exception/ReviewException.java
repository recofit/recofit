package site.recofit.ssafit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.recofit.ssafit.exception.status.ReviewStatus;

@RequiredArgsConstructor
@Getter
public class ReviewException extends RuntimeException {
    private final ReviewStatus status;
}
