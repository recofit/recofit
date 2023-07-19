package site.recofit.ssafit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.recofit.ssafit.exception.status.OAuthStatus;

@RequiredArgsConstructor
@Getter
public class OAuthException extends RuntimeException {
    private final OAuthStatus status;
}
