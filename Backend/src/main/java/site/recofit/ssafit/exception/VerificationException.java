package site.recofit.ssafit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.recofit.ssafit.exception.status.VerificationStatus;

@Getter
@RequiredArgsConstructor
public class VerificationException extends RuntimeException{
    private final VerificationStatus status;
}