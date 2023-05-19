package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.recofit.ssafit.dto.MemberSignupRequestDto;
import site.recofit.ssafit.dto.MemberSignupResponseDto;
import site.recofit.ssafit.service.MemberService;

@RestController
@RequiredArgsConstructor
public class Membercontroller {
    private final MemberService memberServiceImpl;

    public ResponseEntity<MemberSignupResponseDto> signup(final MemberSignupRequestDto requestDto) {
        MemberSignupResponseDto responseDto = memberServiceImpl.signup(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
