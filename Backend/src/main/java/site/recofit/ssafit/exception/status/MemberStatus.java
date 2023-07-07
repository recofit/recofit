package site.recofit.ssafit.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MemberStatus {
    // Member info
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다."),
    NOT_EXISTING_MEMBER(HttpStatus.NOT_FOUND, "일치하는 유저가 없습니다."),

    // Verification info
    UNVERIFIED_EMAIL(HttpStatus.BAD_REQUEST, "인증되지 않은 이메일입니다."),
    DIFFERENT_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "잘못된 코드입니다."),

    // Following info
    ALREADY_FOLLOWING_MEMBER(HttpStatus.BAD_REQUEST, "이미 팔로잉하는 회원입니다."),
    ALREADY_UNFOLLOWING_MEMBER(HttpStatus.BAD_REQUEST, "이미 팔로잉 취소한 회원입니다."),

    // Content Type info
    NO_CONTENT_TYPE(HttpStatus.NOT_FOUND, "컨텐트 타입이 없습니다."),
    NOT_SUPPORTING_TYPE(HttpStatus.BAD_REQUEST, "지원하는 타입이 아닙니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
