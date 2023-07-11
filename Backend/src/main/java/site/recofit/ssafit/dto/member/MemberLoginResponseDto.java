package site.recofit.ssafit.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class MemberLoginResponseDto {
    private final int id;
    private final String picture;
    private final String password;
    private final String nickname;
    private final String accessToken;
    private final String refreshToken;
}