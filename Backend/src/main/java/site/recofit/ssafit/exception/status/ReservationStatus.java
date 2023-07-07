package site.recofit.ssafit.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ReservationStatus {
    UNREQUESTED_EMAIL_VERIFICATION(HttpStatus.BAD_REQUEST, "must request email verification"),;

    private final HttpStatus httpStatus;
    private final String message;
}
