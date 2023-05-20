package site.recofit.ssafit.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Builder
@Getter
public class MemberUpdateResponseDto {
    private final String nickname;
}
