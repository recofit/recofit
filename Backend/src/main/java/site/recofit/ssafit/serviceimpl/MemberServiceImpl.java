package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.recofit.ssafit.dao.MemberDao;
import site.recofit.ssafit.dao.VerificationDao;
import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.domain.Verification;
import site.recofit.ssafit.dto.member.*;
import site.recofit.ssafit.exception.MemberException;
import site.recofit.ssafit.exception.status.MemberStatus;
import site.recofit.ssafit.properties.GmailProperties;
import site.recofit.ssafit.properties.aws.AwsStorageProperties;
import site.recofit.ssafit.service.MemberService;
import site.recofit.ssafit.utility.aws.AwsS3Manager;
import site.recofit.ssafit.utility.common.FileUtility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDao memberDao;
    private final VerificationDao verificationDao;

    private final JavaMailSender mailSender;

    private final AwsS3Manager awsS3Manager;

    private final GmailProperties gmailProperties;
    private final AwsStorageProperties awsStorageProperties;

    public boolean checkEmailDuplication(final String email) {
        return memberDao.findByEmail(email).isPresent();
    }

    public boolean checkNicknameDuplication(final String nickname) {
        return memberDao.findByNickname(nickname).isPresent();
    }

    public boolean checkVerification(final String email) {
        final Verification verification = verificationDao.findByEmail(email).orElseThrow(
                () -> new MemberException(MemberStatus.UNVERIFIED_EMAIL)
        );

        return verification.isState();
    }

    @Transactional
    public MemberSignupResponseDto signup(final MemberSignupRequestDto requestDto) {
        if (checkEmailDuplication(requestDto.getEmail())) {
            throw new MemberException(MemberStatus.DUPLICATED_EMAIL);
        }

        if (checkNicknameDuplication(requestDto.getNickname())) {
            throw new MemberException(MemberStatus.DUPLICATED_NICKNAME);
        }

        if (!checkVerification(requestDto.getEmail())) {
            throw new MemberException(MemberStatus.UNVERIFIED_EMAIL);
        }

        final Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .nickname(requestDto.getNickname())
                .picture(awsStorageProperties.getUrl() + Member.BASIC_PICTURE)
                .build();

        memberDao.save(member);

        return MemberSignupResponseDto.builder()
                .nickname(member.getNickname())
                .build();
    }

    public MemberLoginResponseDto login(final MemberLoginRequestDto requestDto) {
        final Member member = memberDao.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new MemberException(MemberStatus.NOT_EXISTING_MEMBER)
        );

        return MemberLoginResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .picture(member.getPicture())
                .build();
    }

    @Transactional
    public void verificationSender(final String email) throws MessagingException {
        if (checkEmailDuplication(email)) {
            throw new MemberException(MemberStatus.DUPLICATED_EMAIL);
        }

        if (verificationDao.findByEmail(email).isEmpty()) {
            createBasicVerification(email);
        }

        final Verification verification = verificationDao.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberStatus.UNVERIFIED_EMAIL)
        );

        unverify(email);

        final String code = verification.refreshCode();

        refreshCode(email, code);

        final MimeMessage message = mailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("SSAFIT verification code");
        helper.setText("This is your verification code: " + code);

        mailSender.send(message);
    }

    @Transactional
    public void contactMailSender(final MemberContactMailRequestDto requestDto) throws MessagingException {
        final MimeMessage message = mailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(gmailProperties.getUsername());
        helper.setSubject(requestDto.getNickname() + "의 문의입니다.");
        helper.setText("이메일은 " + requestDto.getEmail() + "입니다.\n\n 문의 내용 : \n" + requestDto.getContent());

        mailSender.send(message);
    }

    @Transactional
    public void createBasicVerification(final String email) {
        final Verification verification = Verification.builder()
                .email(email)
                .build();

        verificationDao.save(verification);
    }

    @Transactional
    public void verification(final String code) {
        final Verification verification = verificationDao.findByCode(code)
                .orElseThrow(() -> new MemberException(MemberStatus.DIFFERENT_VERIFICATION_CODE)
        );

        if (code.equals(verification.getCode())) {
            verify(verification.getEmail());
        }
    }

    @Transactional
    public void unverify(final String email) {
        if (checkVerification(email)) {
            verificationDao.unverify(email);
        }
    }

    @Transactional
    public void verify(final String email) {
        if (!checkVerification(email)) {
            verificationDao.verify(email);
        }
    }

    @Transactional
    public void refreshCode(final String email, final String code) {
        if (verificationDao.findByEmail(email).isEmpty()) {
            throw new MemberException(MemberStatus.UNVERIFIED_EMAIL);
        }

        verificationDao.refreshCode(code, email);
    }

    @Transactional
    public void follow(final int followerId, final String followingName) {
        final Member member = memberDao.findByNickname(followingName).orElseThrow(
                () -> new MemberException(MemberStatus.NOT_EXISTING_MEMBER)
        );

        if (memberDao.findByFollowerIdAndFollowingId(followerId, member.getId())) {
            throw new MemberException(MemberStatus.ALREADY_FOLLOWING_MEMBER);
        }

        memberDao.follow(followerId, member.getId());
    }

    @Transactional
    public void unfollow(final int followerId, final int followingId) {
        if (!memberDao.findByFollowerIdAndFollowingId(followerId, followingId)) {
            throw new MemberException(MemberStatus.ALREADY_UNFOLLOWING_MEMBER);
        }

        memberDao.unfollow(followerId, followingId);
    }

    public List<MemberFollowListResponseDto> selectFollower(final int followingId) {
        final List<Integer> followerList = memberDao.findByFollowingId(followingId);

        return convertFollowListToDtoList(followerList);
    }

    public List<MemberFollowListResponseDto> selectFollowing(final int followerId) {
        final List<Integer> followingList = memberDao.findByFollowerId(followerId);

        return convertFollowListToDtoList(followingList);
    }

    private List<MemberFollowListResponseDto> convertFollowListToDtoList(final List<Integer> list) {
        final List<MemberFollowListResponseDto> dtoList = new ArrayList<>();

        for(final int idx : list) {
            final Member member = memberDao.findById(idx).orElseThrow(
                    () -> new MemberException(MemberStatus.NOT_EXISTING_MEMBER)
            );

            final MemberFollowListResponseDto responseDto = MemberFollowListResponseDto.builder()
                    .id(member.getId())
                    .picture(member.getPicture())
                    .nickname(member.getNickname())
                    .build();

            dtoList.add(responseDto);
        }

        return dtoList;
    }

    public MemberReadResponseDto findMember(final int id) {
        final Member member = memberDao.findById(id).orElseThrow(
                () -> new MemberException(MemberStatus.NOT_EXISTING_MEMBER)
        );

        return MemberReadResponseDto.builder()
                .nickname(member.getNickname())
                .picture(member.getPicture())
                .build();
    }

    @Transactional
    public MemberPictureUploadResponseDto uploadPicture(final int id, final MemberPictureUploadRequestDto requestDto) {
        final Member member = memberDao.findById(id).orElseThrow(
                () -> new MemberException(MemberStatus.NOT_EXISTING_MEMBER)
        );

        final MultipartFile pictureFile = requestDto.getPictureFile();

        final String contentType = pictureFile.getContentType();

        if (contentType == null) {
            throw new MemberException(MemberStatus.NO_CONTENT_TYPE);
        }

        if (!contentType.matches("(^image)(/)\\w*")) {
            throw new MemberException(MemberStatus.NOT_SUPPORTING_TYPE);
        }

        final UnaryOperator<String> titleGenerator = createPictureTitleGenerator(member.getNickname());

        final String picturePath = awsS3Manager.uploadFiles(titleGenerator, List.of(pictureFile)).get(0);

        String storageUrl = awsStorageProperties.getUrl();

        if (!storageUrl.endsWith("/")) {
            storageUrl += '/';
        }

        memberDao.updatePicture(storageUrl + picturePath, member.getEmail());

        return MemberPictureUploadResponseDto.builder()
                .picture(storageUrl + picturePath)
                .build();
    }

    @Transactional
    public MemberUpdateResponseDto updateProfile(final int id, final MemberUpdateRequestDto requestDto) {
        final Member member = memberDao.findById(id).orElseThrow(
                () -> new MemberException(MemberStatus.NOT_EXISTING_MEMBER)
        );

        final String nickname = requestDto.getNickname();

        if (checkNicknameDuplication(nickname)) {
            throw new MemberException(MemberStatus.DUPLICATED_NICKNAME);
        }

        memberDao.updateNickname(nickname, member.getEmail());

        return MemberUpdateResponseDto.builder()
                .nickname(nickname)
                .build();
    }

    public static UnaryOperator<String> createPictureTitleGenerator(final String nickname) {
        return originalFileName -> nickname + Member.PROFILE_PICTURE_PATH + FileUtility.getFileExtension(originalFileName);
    }
}
