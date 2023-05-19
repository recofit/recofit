package site.recofit.ssafit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class MemberLoginResponseDto {
    private final String nickname;
    private final String accessToken;
    private final String refreshToken;
}