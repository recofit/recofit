package site.recofit.ssafit.dto;

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
