package site.recofit.ssafit.security.oauth2.kakao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import site.recofit.ssafit.dao.MemberDao;
import site.recofit.ssafit.dao.OAuthDao;
import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.domain.OAuth;
import site.recofit.ssafit.dto.member.MemberLoginResponseDto;
import site.recofit.ssafit.exception.OAuthException;
import site.recofit.ssafit.exception.status.OAuthStatus;
import site.recofit.ssafit.utility.common.StringUtility;
import site.recofit.ssafit.utility.jwt.JwtProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final MemberDao memberDao;
    private final OAuthDao oAuthDao;
    private final KakaoOAuth2 kakaoOAuth2;
    private final JwtProvider accessTokenProvider;
    private final JwtProvider refreshTokenProvider;

    public MemberLoginResponseDto kakaoLogin(String authorizedCode) throws JSONException {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();
        String picture = userInfo.getPicture();

        // DB 에 중복된 Kakao Id 가 있는지 확인
        Member kakaoUser = memberDao.findByEmail(email).orElse(null);

        if (kakaoUser != null && oAuthDao.findByEmail(email).isEmpty()) {
            throw new OAuthException(OAuthStatus.ALREADY_EXISTING_EMAIL_USER);
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

        Optional<Member> temp = memberDao.findByEmail(email);

        if (temp.isEmpty()) {
            throw new OAuthException(OAuthStatus.KAKAO_LOGIN_FAILURE);
        }

        final Member member = temp.get();

        final Map<String, Integer> payload = new HashMap<>();

        payload.put("id", member.getId());

        final Map<String, Integer> refreshPayload = new HashMap<>();

        refreshPayload.put("id", member.getId());

        return MemberLoginResponseDto.builder()
                .nickname(member.getNickname())
                .accessToken(accessTokenProvider.generate(payload))
                .refreshToken(refreshTokenProvider.generate(refreshPayload))
                .build();
    }
}