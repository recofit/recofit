package site.recofit.ssafit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class MemberSignupResponseDto {
    private String nickname;

    @Builder
    public MemberSignupResponseDto(String nickname) {
        this.nickname = nickname;
    }
}
