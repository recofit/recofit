package site.recofit.ssafit.utility.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SALT = "SSAFIT";

    // 토큰 생성
    // 인자로 키와 벨류가 넘어왔다라고만 처리 (한가지의 정보만 넣는다고 가정)
    public String createToken(String claimId, int data) throws UnsupportedEncodingException {
        return Jwts.builder()
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT") // 헤더완료
                .claim(claimId, data)
                .setExpiration(new Date(System.currentTimeMillis())) // 페이로드 완료
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")) // 서명완료
                .compact();
    }

    // 유효성 검사
    public void valid(String token) throws Exception {
        Jwts.parser().setSigningKey("SSAFIT".getBytes("UTF-8")).parseClaimsJws(token);
    }
}