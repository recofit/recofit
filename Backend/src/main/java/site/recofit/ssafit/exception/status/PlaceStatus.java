package site.recofit.ssafit.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum PlaceStatus {
    NOT_EXISTING_PLACE(HttpStatus.NOT_FOUND, "존재하지 않는 장소입니다."),
    DUPLICATED_PLACE_NAME(HttpStatus.CONFLICT, "이미 존재하는 장소 이름입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
