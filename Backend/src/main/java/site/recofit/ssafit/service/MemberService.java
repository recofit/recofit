package site.recofit.ssafit.service;

import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.dto.member.*;

import javax.mail.MessagingException;
import java.util.List;

public interface MemberService {
    boolean checkEmailDuplication(final String email);

    boolean checkNicknameDuplication(final String nickname);

    boolean checkVerification(final String email);

    MemberSignupResponseDto signup(final MemberSignupRequestDto requestDto);

    Member login(final MemberLoginRequestDto requestDto);

    String verificationSender(final String email) throws MessagingException;

    void createBasicVerification(final String email);

    void verification(final String code);

    void follow(final int followerId, final int followingId);

    void unfollow(final int followerId, final int followingId);

    List<MemberFollowListResponseDto> selectFollower(final int followingId);

    List<MemberFollowListResponseDto> selectFollowing(final int followerId);

    MemberReadResponseDto findMember(final int memberId);

    MemberPictureUploadResponseDto uploadPicture(final int id, final MemberPictureUploadRequestDto requestDto);

    MemberUpdateResponseDto updateProfile(final int id, final MemberUpdateRequestDto requestDto);
}
