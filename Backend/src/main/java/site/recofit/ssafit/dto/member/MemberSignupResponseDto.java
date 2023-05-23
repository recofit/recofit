package site.recofit.ssafit.dto.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
public class MemberSignupResponseDto {
    private String nickname;
}
