package site.recofit.ssafit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.recofit.ssafit.exception.status.ReservationStatus;

@RequiredArgsConstructor
@Getter
public class ReservationException extends RuntimeException {
    private final ReservationStatus status;
}