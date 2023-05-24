package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.dto.member.*;
import site.recofit.ssafit.security.oauth2.kakao.KakaoService;
import site.recofit.ssafit.service.MemberService;
import site.recofit.ssafit.utility.jwt.JwtUtil;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final KakaoService kakaoService;
    private final JwtUtil jwtUtil;

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
    public ResponseEntity<Map<String, Object>> login(@RequestBody final MemberLoginRequestDto requestDto,
                                                        final HttpServletResponse response) {
        Member member = memberService.login(requestDto);

        Map<String, Object> result = new HashMap<>();
        HttpStatus status;

        try {
            if (member != null && member.getPassword().equals(requestDto.getPassword())) {
                result.put("access-token", jwtUtil.createToken("id", member.getId()));
                result.put("message", "SUCCESS");
                result.put("id", member.getId());
                result.put("nickname", member.getNickname());
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (UnsupportedEncodingException e) {
            result.put("message", "FAIL");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);

//        CookieUtility.addCookie(response, AccessTokenProperties.COOKIE_NAME, result.getAccessToken());
//        CookieUtility.addCookie(response, RefreshTokenProperties.COOKIE_NAME, result.getRefreshToken(), 6480000);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<Map<String, Object>> getKakaoRequest(@RequestParam final String code, final HttpServletResponse response) throws JSONException, IOException {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status;

        try {
            final Member member = kakaoService.kakaoLogin(code);
            result.put("access-token", jwtUtil.createToken("id", member.getId()));
            result.put("message", "SUCCESS");
            result.put("id", member.getId());
            result.put("nickname", member.getNickname());
            status = HttpStatus.OK;
        } catch (UnsupportedEncodingException e) {
            result.put("message", "FAIL");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
//        CookieUtility.addCookie(response, AccessTokenProperties.COOKIE_NAME, result.getAccessToken());
//        CookieUtility.addCookie(response, RefreshTokenProperties.COOKIE_NAME, result.getRefreshToken(), 6480000);

        response.sendRedirect("http://localhost:8081");

        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(final HttpSession session) {

        session.invalidate();
//        CookieUtility.deleteCookie(response, AccessTokenProperties.COOKIE_NAME);
//        CookieUtility.deleteCookie(response, RefreshTokenProperties.COOKIE_NAME);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/mailsender")
    public ResponseEntity<String> verificationSender(@RequestParam final String email) throws MessagingException {
        final String code = memberService.verificationSender(email);

        return ResponseEntity.status(HttpStatus.OK).body(code);
    }

    @PostMapping("/verification")
    public ResponseEntity<Void> verify(@RequestParam final String code) {
        memberService.verification(code);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/follow/{followingId}")
    public ResponseEntity<Void> follow(@RequestParam int followerId,
                                       @PathVariable int followingId) {

        memberService.follow(followerId, followingId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unfollow/{followingId}")
    public ResponseEntity<Void> unfollow(@RequestParam int followerId,
                                         @PathVariable int followingId) {

        memberService.unfollow(followerId, followingId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/follower")
    public ResponseEntity<List<MemberFollowListResponseDto>> findFollower(
            @RequestParam int followingId
            ) {
//        final int id = memberDetails.getId();

        List<MemberFollowListResponseDto> responseDtos = memberService.selectFollower(followingId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    @GetMapping("/following")
    public ResponseEntity<List<MemberFollowListResponseDto>> findFollowing(
            @RequestParam int followerId
            ) {

        List<MemberFollowListResponseDto> responseDtos = memberService.selectFollowing(followerId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    @GetMapping("")
    public ResponseEntity<MemberReadResponseDto> findMember(
            @RequestParam int id
    ) {

        final MemberReadResponseDto responseDto = memberService.findMember(id);

        return ResponseEntity.ok().body(responseDto);
    }

    @PatchMapping(value = "")
    public ResponseEntity<MemberUpdateResponseDto> updateProfile(
            @RequestParam int id,
                                                                 @RequestBody final MemberUpdateRequestDto requestDto) {

        final MemberUpdateResponseDto responseDto = memberService.updateProfile(id, requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @PatchMapping(value = "/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MemberPictureUploadResponseDto> uploadPicture(
            @RequestParam int id,
                                                                        @ModelAttribute final MemberPictureUploadRequestDto requestDto) {

        final MemberPictureUploadResponseDto responseDto = memberService.uploadPicture(id, requestDto);

        return ResponseEntity.ok().body(responseDto);
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
