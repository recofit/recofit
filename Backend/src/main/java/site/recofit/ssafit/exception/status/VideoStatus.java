package site.recofit.ssafit.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum VideoStatus {
    EXISTING_VIDEO(HttpStatus.CONFLICT, "이미 존재하는 비디오입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
