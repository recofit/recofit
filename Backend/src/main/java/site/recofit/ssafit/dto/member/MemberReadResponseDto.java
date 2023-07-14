package site.recofit.ssafit.dto.member;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class MemberReadResponseDto {
    private final int id;
    private final String nickname;
    private final String picture;
}
