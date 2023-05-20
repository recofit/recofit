package site.recofit.ssafit.security.userdetails;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import site.recofit.ssafit.properties.jwt.AccessTokenProperties.AccessTokenClaim;
import site.recofit.ssafit.utility.jwt.JwtProvider;

@RequiredArgsConstructor
@Slf4j
public class MemberDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    private final JwtProvider accessTokenProvider;

    @Override
    public UserDetails loadUserDetails(final PreAuthenticatedAuthenticationToken token) throws AuthenticationException {
        try {
            final String accessToken = (String) token.getCredentials();
            final DecodedJWT decodedAccessToken = accessTokenProvider.verify(accessToken);

            final int id = decodedAccessToken.getClaim(AccessTokenClaim.ID.getClaim()).asInt();

            log.info("Member authentication request: {}", id);

            return MemberDetails.builder()
                    .id(id)
                    .build();
        } catch (final JWTVerificationException ex) {
            throw new BadCredentialsException(ex.getMessage());
        }
    }
}
