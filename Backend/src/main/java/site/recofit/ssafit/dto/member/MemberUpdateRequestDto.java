package site.recofit.ssafit.dto.member;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class MemberUpdateRequestDto {
    private final String nickname;
}
