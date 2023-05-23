package site.recofit.ssafit.dto.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class MemberSignupRequestDto {
    private String email;
    private String nickname;
    private String password;
}
