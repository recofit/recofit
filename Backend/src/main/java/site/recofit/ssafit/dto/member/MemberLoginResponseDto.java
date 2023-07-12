package site.recofit.ssafit.dto.member;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class MemberLoginResponseDto {
    private final int id;
    private final String picture;
    private final String password;
    private final String nickname;
    private final String accessToken;
    private final String refreshToken;
}