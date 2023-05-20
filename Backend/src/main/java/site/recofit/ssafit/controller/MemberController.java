package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.MemberLoginRequestDto;
import site.recofit.ssafit.dto.MemberLoginResponseDto;
import site.recofit.ssafit.dto.MemberSignupRequestDto;
import site.recofit.ssafit.dto.MemberSignupResponseDto;
import site.recofit.ssafit.properties.jwt.AccessTokenProperties;
import site.recofit.ssafit.properties.jwt.RefreshTokenProperties;
import site.recofit.ssafit.service.MemberService;
import site.recofit.ssafit.serviceimpl.MemberServiceImpl;
import site.recofit.ssafit.utility.common.CookieUtility;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private MemberService memberService;

    public void setMemberService(MemberServiceImpl memberServiceImpl) {
        this.memberService = memberServiceImpl;
    }

    @RequestMapping(value = "/emailcheck/{email}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkEmailDuplication(@PathVariable final String email) {
        return (memberService.checkEmailDuplication(email)) ?
                (ResponseEntity.ok().build()) :
                (ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/nicknamecheck/{nickname}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkNicknameDuplication(@PathVariable final String nickname) {
        return (memberService.checkNicknameDuplication(nickname)) ?
                (ResponseEntity.ok().build()) :
                (ResponseEntity.notFound().build());
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberSignupResponseDto> register(@RequestBody final MemberSignupRequestDto requestDto) {
        final MemberSignupResponseDto result = memberService.signup(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody final MemberLoginRequestDto requestDto,
                                                        final HttpServletResponse response) {
        final MemberLoginResponseDto result = memberService.login(requestDto);

        CookieUtility.addCookie(response, AccessTokenProperties.COOKIE_NAME, result.getAccessToken());
        CookieUtility.addCookie(response, RefreshTokenProperties.COOKIE_NAME, result.getRefreshToken(), 6480000);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(final HttpServletResponse response) {

        CookieUtility.deleteCookie(response, AccessTokenProperties.COOKIE_NAME);
        CookieUtility.deleteCookie(response, RefreshTokenProperties.COOKIE_NAME);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/mailsender")
    public String verificationSender(@RequestParam final String email) throws MessagingException {
        return memberService.verificationSender(email);
    }

    @PostMapping("/verification")
    public void verify(@RequestParam final String code) {
        memberService.verification(code);
    }
}
