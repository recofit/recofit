package site.recofit.ssafit.dto.member;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class MemberSignupRequestDto {
    private final String email;
    private final String nickname;
    private final String password;
}
