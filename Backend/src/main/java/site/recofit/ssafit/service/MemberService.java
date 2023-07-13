package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.member.*;

import javax.mail.MessagingException;
import java.util.List;

public interface MemberService {
    // 회원 정보 중복 확인
    boolean checkEmailDuplication(final String email);

    boolean checkNicknameDuplication(final String nickname);

    boolean checkVerification(final String email);

    // 회원가입
    MemberSignupResponseDto signup(final MemberSignupRequestDto requestDto);

    // 로그인
    MemberLoginResponseDto login(final MemberLoginRequestDto requestDto);

    // 메일
    void verificationSender(final String email) throws MessagingException;

    void contactMailSender(final MemberContactMailRequestDto requestDto) throws MessagingException;

    // 메일 인증
    void createBasicVerification(final String email);

    void verification(final String code);

    // 팔로우
    void follow(final int followerId, final String followingName);

    void unfollow(final int followerId, final int followingId);

    List<MemberFollowListResponseDto> selectFollower(final int followingId);

    List<MemberFollowListResponseDto> selectFollowing(final int followerId);

    // 회원 확인
    MemberReadResponseDto getMember(final int memberId);

    // 회원 수정
    MemberPictureUploadResponseDto uploadPicture(final int id, final MemberPictureUploadRequestDto requestDto);

    MemberUpdateResponseDto updateProfile(final int id, final MemberUpdateRequestDto requestDto);
}
