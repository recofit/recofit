package site.recofit.ssafit.properties.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt.access-token")
public class AccessTokenProperties extends JwtProperties {
    public static final String COOKIE_NAME = "access_token";

    public AccessTokenProperties(final String secretKey, final int validSeconds) {
        super(secretKey, validSeconds);
    }

    @RequiredArgsConstructor
    @Getter
    public enum AccessTokenClaim {
        STUDENT_ID("studentId"),
        NICKNAME("nickname"),
        ROLE("role");

        private final String claim;
    }
}