package site.recofit.ssafit.security.oauth2.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserInfo {
    private Long id;
    private String email;
    private String nickname;
    private String picture;
}
