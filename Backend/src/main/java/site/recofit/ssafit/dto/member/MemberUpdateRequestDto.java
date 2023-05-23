package site.recofit.ssafit.dto.member;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Builder
@Getter
public class MemberUpdateRequestDto {
    private final String nickname;
}
