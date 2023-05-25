package site.recofit.ssafit.dto.member;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class MemberContactMailRequestDto {
    private final String email;
    private final String nickname;
    private final String content;
}
