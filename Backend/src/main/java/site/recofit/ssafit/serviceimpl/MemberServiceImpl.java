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

    private final GmailProperties gmailProperties;
    private final AwsS3Manager awsS3Manager;
    private final AwsStorageProperties awsStorageProperties;

    public boolean checkEmailDuplication(final String email) {
        return memberDao.findByEmail(email).isPresent();
    }

    public boolean checkNicknameDuplication(final String nickname) {
        return memberDao.findByNickname(nickname).isPresent();
    }

    public boolean checkVerification(final String email) {
        final Verification verification = verificationDao.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("인증 요청을 받지 않은 이메일입니다.")
        );

        return verification.isState();
    }

    @Transactional
    public MemberSignupResponseDto signup(final MemberSignupRequestDto requestDto) {
        if (checkEmailDuplication(requestDto.getEmail()))
            throw new IllegalArgumentException("중복된 이메일입니다.");

        if (checkNicknameDuplication(requestDto.getNickname()))
            throw new IllegalArgumentException("중복된 닉네임입니다.");

        if (!checkVerification(requestDto.getEmail())) {
            throw new IllegalArgumentException("아직 인증되지 않았습니다.");
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

    public Member login(final MemberLoginRequestDto requestDto) {
        Member member = memberDao.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("일치하는 유저가 없습니다.")
        );

        return member;
    }

    @Transactional
    public String verificationSender(final String email) throws MessagingException {
        if (checkEmailDuplication(email)) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }

        if (verificationDao.findByEmail(email).isEmpty()) {
            createBasicVerification(email);
        }

        final Verification verification = verificationDao.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("다시 시도해주세요.")
        );

        unverify(email);

        String code = verification.refreshCode();
        refreshCode(email, code);

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("SSAFIT verification code");
        helper.setText("This is your verification code: " + code);

        mailSender.send(message);

        return code;
    }

    @Transactional
    public void contactMailSender(final MemberContactMailRequestDto requestDto) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코드입니다."));

        if (code.equals(verification.getCode())) {
            verify(verification.getEmail());
        }
    }

    @Transactional
    public void unverify(final String email) {
        final Verification verification = verificationDao.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일 인증을 진행해주세요."));

        if (verification.isState()) {
            verificationDao.unverify(email);
        }
    }

    @Transactional
    public void verify(final String email) {
        final Verification verification = verificationDao.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일 인증을 진행해주세요."));

        if (!verification.isState()) {
            verificationDao.verify(email);
        }
    }

    @Transactional
    public void refreshCode(final String email, final String code) {
        if (verificationDao.findByEmail(email).isEmpty())
            throw new IllegalArgumentException("다시 시도해주세요.");

        verificationDao.refreshCode(code, email);
    }

    @Transactional
    public void follow(final int followerId, final int followingId) {
        if (memberDao.findByFollowerIdAndFollowingId(followerId, followingId))
            throw new IllegalArgumentException("이미 팔로잉하는 회원입니다.");

        memberDao.follow(followerId, followingId);
    }

    @Transactional
    public void unfollow(final int followerId, final int followingId) {
        if (!memberDao.findByFollowerIdAndFollowingId(followerId, followingId))
            throw new IllegalArgumentException("이미 팔로우하지 않는 회원입니다.");

        memberDao.unfollow(followerId, followingId);
    }

    public List<MemberFollowListResponseDto> selectFollower(final int followingId) {
        List<Integer> followerList = memberDao.findByFollowingId(followingId);

        System.out.println(1);
        return convertFollowListToDtoList(followerList);
    }

    public List<MemberFollowListResponseDto> selectFollowing(final int followerId) {
        List<Integer> followingList = memberDao.findByFollowerId(followerId);

        return convertFollowListToDtoList(followingList);
    }

    private List<MemberFollowListResponseDto> convertFollowListToDtoList(final List<Integer> list) {
        List<MemberFollowListResponseDto> dtoList = new ArrayList<>();

        for(int idx : list) {
            Member member = memberDao.findById(idx).orElseThrow(
                    () -> new IllegalArgumentException("정보를 불러오지 못했습니다.")
            );

            MemberFollowListResponseDto responseDto = MemberFollowListResponseDto.builder()
                    .id(member.getId())
                    .nickname(member.getNickname())
                    .build();

            dtoList.add(responseDto);
        }

        return dtoList;
    }

    public MemberReadResponseDto findMember(final int id) {
        Member member = memberDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("당신은 당신이 아닙니다.")
        );

        return MemberReadResponseDto.builder()
                .nickname(member.getNickname())
                .picture(member.getPicture())
                .build();
    }

    @Transactional
    public MemberPictureUploadResponseDto uploadPicture(final int id, final MemberPictureUploadRequestDto requestDto) {
        Member member = memberDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 회원이 없습니다.")
        );

        final MultipartFile pictureFile = requestDto.getPictureFile();

        final String contentType = pictureFile.getContentType();

        if (contentType == null) {
            throw new IllegalArgumentException("컨텐트 타입이 없습니다.");
        }

        if (!contentType.matches("(^image)(/)\\w*")) {
            throw new IllegalArgumentException("지원하는 타입이 아닙니다.");
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
        Member member = memberDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 회원이 없습니다.")
        );

        final String nickname = requestDto.getNickname();

        if (checkNicknameDuplication(nickname)) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        memberDao.updateNickname(nickname, member.getEmail());

        return MemberUpdateResponseDto.builder()
                .nickname(nickname)
                .build();
    }

    public static UnaryOperator<String> createPictureTitleGenerator(final String nickname) {
        return originalFileName -> nickname + "/profile/picture" + FileUtility.getFileExtension(originalFileName);
    }
}
