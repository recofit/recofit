package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.MemberSignupRequestDto;
import site.recofit.ssafit.dto.MemberSignupResponseDto;

public interface MemberService {
    MemberSignupResponseDto signup(final MemberSignupRequestDto requestDto);
}
