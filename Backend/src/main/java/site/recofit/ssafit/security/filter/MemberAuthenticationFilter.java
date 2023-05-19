package site.recofit.ssafit.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import site.recofit.ssafit.security.authentication.MemberAuthenticationConverter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberAuthenticationFilter extends AuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public MemberAuthenticationFilter(AuthenticationManager authenticationManager,
                                      AuthenticationConverter authenticationConverter) {
        super(authenticationManager, authenticationConverter);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            MemberAuthenticationConverter authenticationConverter = new MemberAuthenticationConverter();
            Authentication jwtAuthenticationToken = authenticationConverter.convert(request);
            Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException authenticationException) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
