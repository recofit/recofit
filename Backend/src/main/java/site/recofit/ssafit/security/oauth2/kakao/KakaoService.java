package site.recofit.ssafit.security.oauth2.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import site.recofit.ssafit.dao.MemberDao;
import site.recofit.ssafit.dao.OAuthDao;
import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.domain.OAuth;
import site.recofit.ssafit.utility.common.StringUtility;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final MemberDao memberDao;
    private final OAuthDao oAuthDao;
    private final KakaoOAuth2 kakaoOAuth2;

    public Member kakaoLogin(String authorizedCode) throws JSONException {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();
        String picture = userInfo.getPicture();

        // DB 에 중복된 Kakao Id 가 있는지 확인
        Member kakaoUser = memberDao.findByEmail(email).orElse(null);

        if (kakaoUser != null && oAuthDao.findByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("이메일로 가입한 이메일입니다.");
        }

        // 카카오 정보로 회원가입
        if (kakaoUser == null) {
            kakaoUser = Member.builder()
                    .nickname(nickname)
                    .email(email)
                    .picture(picture)
                    .password(StringUtility.generateRandomString(20))
                    .build();

            memberDao.save(kakaoUser);

            OAuth oauth = OAuth.builder()
                    .email(email)
                    .build();

            oAuthDao.save(oauth);
        }

        Optional<Member> result = memberDao.findByEmail(email);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("카카오 로그인 중 가입에 실패했습니다.");
        }

        return result.get();
    }
}