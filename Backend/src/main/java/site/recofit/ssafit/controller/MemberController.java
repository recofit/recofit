package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.member.*;
import site.recofit.ssafit.properties.jwt.AccessTokenProperties;
import site.recofit.ssafit.properties.jwt.RefreshTokenProperties;
import site.recofit.ssafit.security.oauth2.kakao.KakaoService;
import site.recofit.ssafit.security.userdetails.MemberDetails;
import site.recofit.ssafit.service.MemberService;
import site.recofit.ssafit.utility.common.CookieUtility;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final KakaoService kakaoService;

    @RequestMapping(value = "/emailcheck/{email}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkEmailDuplication(@PathVariable final String email) {
        return (!memberService.checkEmailDuplication(email)) ?
                (ResponseEntity.ok().build()) :
                (ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/nicknamecheck/{nickname}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkNicknameDuplication(@PathVariable final String nickname) {
        return (!memberService.checkNicknameDuplication(nickname)) ?
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

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(final HttpServletResponse response) {

        CookieUtility.deleteCookie(response, AccessTokenProperties.COOKIE_NAME);
        CookieUtility.deleteCookie(response, RefreshTokenProperties.COOKIE_NAME);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/mailsender/{email}")
    public ResponseEntity<String> verificationSender(@PathVariable final String email) throws MessagingException {
        final String code = memberService.verificationSender(email);

        return ResponseEntity.status(HttpStatus.OK).body(code);
    }

    @PostMapping("/verification")
    public ResponseEntity<Void> verify(@RequestParam final String code) {
        memberService.verification(code);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/follow/{followingId}")
    public ResponseEntity<Void> follow(@AuthenticationPrincipal final MemberDetails memberDetails,
                                       @PathVariable int followingId) {
        final int followerId = memberDetails.getId();

        memberService.follow(followerId, followingId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unfollow/{followingId}")
    public ResponseEntity<Void> unfollow(@AuthenticationPrincipal final MemberDetails memberDetails,
                                         @PathVariable int followingId) {
        final int followerId = memberDetails.getId();

        memberService.unfollow(followerId, followingId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/follower")
    public ResponseEntity<List<MemberFollowListResponseDto>> findFollower(@AuthenticationPrincipal final MemberDetails memberDetails) {
        final int id = memberDetails.getId();

        List<MemberFollowListResponseDto> responseDtos = memberService.selectFollower(id);

        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    @GetMapping("/following")
    public ResponseEntity<List<MemberFollowListResponseDto>> findFollowing(@AuthenticationPrincipal final MemberDetails memberDetails) {
        final int id = memberDetails.getId();

        List<MemberFollowListResponseDto> responseDtos = memberService.selectFollowing(id);

        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    @GetMapping("")
    public ResponseEntity<MemberReadResponseDto> findMember(@AuthenticationPrincipal final MemberDetails memberDetails) {
        final int id = memberDetails.getId();

        final MemberReadResponseDto responseDto = memberService.findMember(id);

        return ResponseEntity.ok().body(responseDto);
    }

    @PatchMapping(value = "")
    public ResponseEntity<MemberUpdateResponseDto> updateProfile(@AuthenticationPrincipal final MemberDetails memberDetails,
                                                                 @RequestBody final MemberUpdateRequestDto requestDto) {
        final int id = memberDetails.getId();

        final MemberUpdateResponseDto responseDto = memberService.updateProfile(id, requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @PatchMapping(value = "/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MemberPictureUploadResponseDto> uploadPicture(@AuthenticationPrincipal final MemberDetails memberDetails,
                                                                        @ModelAttribute final MemberPictureUploadRequestDto requestDto) {
        final int id = memberDetails.getId();

        final MemberPictureUploadResponseDto responseDto = memberService.uploadPicture(id, requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> getKakaoRequest(@RequestParam final String code, final HttpServletResponse response) throws JSONException, IOException {
        final MemberLoginResponseDto result = kakaoService.kakaoLogin(code);

        CookieUtility.addCookie(response, AccessTokenProperties.COOKIE_NAME, result.getAccessToken());
        CookieUtility.addCookie(response, RefreshTokenProperties.COOKIE_NAME, result.getRefreshToken(), 6480000);

        response.sendRedirect("http://localhost:8081");

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/example/{pathVariableId}")
    public ResponseEntity<Void> example(
//            @AuthenticationPrincipal final MemberDetails memberDetails,
            @PathVariable int pathVariableId) {
//        final int exampleId2 = memberDetails.getId();
        final int exampleId = 1;

        memberService.follow(exampleId, pathVariableId);

        return ResponseEntity.ok().build();
    }
}
