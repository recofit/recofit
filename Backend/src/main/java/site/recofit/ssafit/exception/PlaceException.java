package site.recofit.ssafit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.recofit.ssafit.exception.status.PlaceStatus;

@RequiredArgsConstructor
@Getter
public class PlaceException extends RuntimeException {
    private final PlaceStatus status;
}