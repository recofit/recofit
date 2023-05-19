package site.recofit.ssafit.properties.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class JwtProperties {
    private final String secretKey;

    private final int validSeconds;
}
