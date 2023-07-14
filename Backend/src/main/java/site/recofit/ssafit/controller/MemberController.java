package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.member.*;
import site.recofit.ssafit.properties.jwt.AccessTokenProperties;
import site.recofit.ssafit.properties.jwt.RefreshTokenProperties;
import site.recofit.ssafit.security.oauth2.kakao.KakaoService;
import site.recofit.ssafit.service.MemberService;
import site.recofit.ssafit.utility.common.CookieUtility;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    private final KakaoService kakaoService;

    @RequestMapping(value = "/emailcheck/{email}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkEmailDuplication(@PathVariable final String email) {
        return (!memberService.checkEmailDuplication(email)) ?
                (ResponseEntity.status(HttpStatus.OK).build()) :
                (ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @RequestMapping(value = "/nicknamecheck/{nickname}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkNicknameDuplication(@PathVariable final String nickname) {
        return (!memberService.checkNicknameDuplication(nickname)) ?
                (ResponseEntity.status(HttpStatus.OK).build()) :
                (ResponseEntity.status(HttpStatus.NOT_FOUND).build());
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

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> getKakaoRequest(@RequestParam final String code, final HttpServletResponse response) throws JSONException, IOException {
        final MemberLoginResponseDto result = kakaoService.kakaoLogin(code);

        CookieUtility.addCookie(response, AccessTokenProperties.COOKIE_NAME, result.getAccessToken());
        CookieUtility.addCookie(response, RefreshTokenProperties.COOKIE_NAME, result.getRefreshToken(), 6480000);

        response.sendRedirect("http://localhost:8081");

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(final HttpServletResponse response) {

        CookieUtility.deleteCookie(response, AccessTokenProperties.COOKIE_NAME);
        CookieUtility.deleteCookie(response, RefreshTokenProperties.COOKIE_NAME);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/mailsender/{email}")
    public ResponseEntity<Void> verificationSender(@PathVariable final String email) throws MessagingException {
        memberService.verificationSender(email);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/mailsender")
    public ResponseEntity<Void> contactMailSender(@RequestBody final MemberContactMailRequestDto requestDto) throws MessagingException {
        memberService.contactMailSender(requestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/verification")
    public ResponseEntity<Void> verify(@RequestParam final String code) {
        memberService.verification(code);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/follow/{followingName}")
    public ResponseEntity<Void> follow(@RequestParam final int followerId,
                                       @PathVariable final String followingName) {
        memberService.follow(followerId, followingName);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/unfollow/{followingId}")
    public ResponseEntity<Void> unfollow(@RequestParam final int followerId,
                                         @PathVariable final int followingId) {
        memberService.unfollow(followerId, followingId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/follower")
    public ResponseEntity<List<MemberFollowListResponseDto>> findFollower(@RequestParam final int memberId) {
        final List<MemberFollowListResponseDto> responseDtos = memberService.selectFollower(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    @GetMapping("/following")
    public ResponseEntity<List<MemberFollowListResponseDto>> findFollowing(@RequestParam final int memberId) {
        final List<MemberFollowListResponseDto> responseDtos = memberService.selectFollowing(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    @GetMapping("")
    public ResponseEntity<MemberReadResponseDto> getMember(@RequestParam final int memberId) {
        final MemberReadResponseDto responseDto = memberService.getMember(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping(value = "/{memberId}")
    public ResponseEntity<MemberUpdateResponseDto> updateProfile(@PathVariable final int memberId,
                                                                 @RequestBody final MemberUpdateRequestDto requestDto) {
        final MemberUpdateResponseDto responseDto = memberService.updateProfile(memberId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping(value = "/picture/{memberId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MemberPictureUploadResponseDto> uploadPicture(@PathVariable final int memberId,
                                                                        @ModelAttribute final MemberPictureUploadRequestDto requestDto) {
        final MemberPictureUploadResponseDto responseDto = memberService.uploadPicture(memberId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
