package site.recofit.ssafit.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ReviewStatus {
    NOT_EXISTING_REVIEW(HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
