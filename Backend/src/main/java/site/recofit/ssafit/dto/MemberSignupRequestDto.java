package site.recofit.ssafit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class MemberSignupRequestDto {
    private String email;
    private String nickname;
    private String password;
    private String picture;
}
