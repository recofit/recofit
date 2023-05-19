package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.MemberSignupRequestDto;
import site.recofit.ssafit.dto.MemberSignupResponseDto;
import site.recofit.ssafit.service.MemberService;
import site.recofit.ssafit.serviceimpl.MemberServiceImpl;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberServiceImpl memberServiceImpl) {
        this.memberService = memberServiceImpl;
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkEmailDuplication(@PathVariable final String email) {
        return (memberService.checkEmailDuplication(email)) ?
                (ResponseEntity.ok().build()) :
                (ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/nickname/{nickname}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkNicknameDuplication(@PathVariable final String nickname) {
        return (memberService.checkNicknameDuplication(nickname)) ?
                (ResponseEntity.ok().build()) :
                (ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<MemberSignupResponseDto> register(@RequestBody final MemberSignupRequestDto requestDto) {
        final MemberSignupResponseDto result = memberService.signup(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/mailSender")
    public String verificationSender(@RequestParam final String email) throws MessagingException {
        return memberService.verificationSender(email);
    }

    @PostMapping("/verify")
    public void verify(@RequestParam final String code) {
        memberService.verification(code);
    }
}
