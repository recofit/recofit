package site.recofit.ssafit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
public class MemberSignupResponseDto {
    private String nickname;
}
