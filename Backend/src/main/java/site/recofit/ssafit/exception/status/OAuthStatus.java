package site.recofit.ssafit.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum OAuthStatus {
    ALREADY_EXISTING_EMAIL_USER(HttpStatus.NOT_FOUND, "동일한 이메일로 가입된 계정입니다."),
    KAKAO_LOGIN_FAILURE(HttpStatus.CONFLICT, "카카오 로그인 중 가입에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
