package site.recofit.ssafit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import site.recofit.ssafit.exception.handler.security.AccessDeniedExceptionHandler;
import site.recofit.ssafit.exception.handler.security.AuthenticationExceptionHandler;
import site.recofit.ssafit.properties.jwt.AccessTokenProperties;
import site.recofit.ssafit.properties.jwt.RefreshTokenProperties;
import site.recofit.ssafit.properties.security.SecurityCorsProperties;
import site.recofit.ssafit.security.authentication.MemberAuthenticationConverter;
import site.recofit.ssafit.security.authentication.MemberAuthenticationProvider;
import site.recofit.ssafit.security.filter.MemberAuthenticationFilter;
import site.recofit.ssafit.security.userdetails.MemberDetailsService;
import site.recofit.ssafit.utility.jwt.JwtProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   CorsConfigurationSource corsConfigurationSource,
                                                   AuthenticationFilter authenticationFilter,
                                                   AuthenticationEntryPoint authenticationEntryPoint,
                                                   AccessDeniedHandler accessDeniedHandler) throws Exception {
        http.cors().configurationSource(corsConfigurationSource);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .rememberMe().disable();

        // 권한 설정
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .anyRequest().permitAll();

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(final SecurityCorsProperties properties) {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(properties.isAllowCredentials());
        corsConfiguration.setAllowedHeaders(properties.getAllowedHeaders());
        corsConfiguration.setAllowedMethods(properties.getAllowedMethods());
        corsConfiguration.setAllowedOrigins(properties.getAllowedOrigins());
        corsConfiguration.setMaxAge(corsConfiguration.getMaxAge());

        final UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();

        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsConfigurationSource;
    }

    // spring security customize
    @Bean
    public AuthenticationFilter authenticationFilter(final AuthenticationManager authenticationManager,
                                                     final AuthenticationConverter authenticationConverter,
                                                     final AuthenticationSuccessHandler authenticationSuccessHandler,
                                                     final AuthenticationFailureHandler authenticationFailureHandler) {
        final MemberAuthenticationFilter authenticationFilter =
                new MemberAuthenticationFilter(authenticationManager, authenticationConverter);

        authenticationFilter.setSuccessHandler(authenticationSuccessHandler);
        authenticationFilter.setFailureHandler(authenticationFailureHandler);

        return authenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationConverter authenticationConverter() {
        return new MemberAuthenticationConverter();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(final AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService) {
        return new MemberAuthenticationProvider(authenticationUserDetailsService);
    }

    @Bean
    public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService(final @Qualifier("accessTokenProvider") JwtProvider accessTokenProvider) {
        return new MemberDetailsService(accessTokenProvider);
    }

    // Handler
    @Bean(name = "authenticationSuccessHandler")
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(final AuthenticationEntryPoint authenticationEntryPoint) {
        return new AuthenticationEntryPointFailureHandler(authenticationEntryPoint);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(final JwtProvider accessTokenProvider,
                                                             final JwtProvider refreshTokenProvider,
                                                             final ObjectMapper objectMapper) {
        return new AuthenticationExceptionHandler(accessTokenProvider, refreshTokenProvider, objectMapper);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(final ObjectMapper objectMapper) {
        return new AccessDeniedExceptionHandler(objectMapper);
    }

    // token provider
    @Bean(name = "accessTokenProvider")
    public JwtProvider accessTokenProvider(final AccessTokenProperties properties) {
        return new JwtProvider(properties);
    }

    @Bean(name = "refreshTokenProvider")
    public JwtProvider refreshTokenProvider(final RefreshTokenProperties properties) {
        return new JwtProvider(properties);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
