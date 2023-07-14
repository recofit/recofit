package site.recofit.ssafit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.recofit.ssafit.exception.status.MemberStatus;

@RequiredArgsConstructor
@Getter
public class MemberException extends RuntimeException {
    private final MemberStatus status;
}