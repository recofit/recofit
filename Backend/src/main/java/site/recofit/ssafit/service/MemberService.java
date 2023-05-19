package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.MemberSignupRequestDto;
import site.recofit.ssafit.dto.MemberSignupResponseDto;

import javax.mail.MessagingException;

public interface MemberService {
    boolean checkEmailDuplication(final String email);

    boolean checkNicknameDuplication(final String nickname);

    boolean checkVerification(final String email);

    MemberSignupResponseDto signup(final MemberSignupRequestDto requestDto);

    String verificationSender(final String email) throws MessagingException;

    void createBasicVerification(final String email);

    void verification(final String code);
}
