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
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    private final KakaoService kakaoService;

    private final JwtUtil jwtUtil;

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
    public ResponseEntity<Map<String, Object>> login(@RequestBody final MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto responseDto = memberService.login(requestDto);

        Map<String, Object> result = new HashMap<>();
        HttpStatus status;

        try {
            if (responseDto != null && responseDto.getPassword().equals(requestDto.getPassword())) {
                result.put("access-token", jwtUtil.createToken("id", responseDto.getId()));
                result.put("message", "SUCCESS");
                result.put("id", responseDto.getId());
                result.put("nickname", responseDto.getNickname());
                result.put("picture", responseDto.getPicture());
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (UnsupportedEncodingException e) {
            result.put("message", "FAIL");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
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
            result.put("picture", member.getPicture());
            status = HttpStatus.OK;
        } catch (UnsupportedEncodingException e) {
            result.put("message", "FAIL");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.sendRedirect("http://localhost:8081/");

        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(final HttpSession session) {
        session.invalidate();

        return ResponseEntity.status(HttpStatus.OK).build();
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
