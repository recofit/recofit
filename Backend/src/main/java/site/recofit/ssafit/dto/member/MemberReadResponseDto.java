package site.recofit.ssafit.dto.member;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Builder
@Setter
public class MemberReadResponseDto {
    private final int id;
    private final String nickname;
    private final String picture;
}
