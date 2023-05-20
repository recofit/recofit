package site.recofit.ssafit.serviceimpl;

import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.MemberDao;
import site.recofit.ssafit.dao.VerificationDao;
import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.domain.Verification;
import site.recofit.ssafit.dto.*;
import site.recofit.ssafit.exception.MemberException;
import site.recofit.ssafit.exception.status.MemberStatus;
import site.recofit.ssafit.service.MemberService;
import site.recofit.ssafit.utility.jwt.JwtProvider;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    public static final String BASIC_PICTURE = "https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo=w240-h480-rw";

    private final MemberDao memberDao;
    private final VerificationDao verificationDao;

    private final JwtProvider accessTokenProvider;
    private final JwtProvider refreshTokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender mailSender;

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
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .nickname(requestDto.getNickname())
                .picture(BASIC_PICTURE)
                .build();

        memberDao.save(member);

        return MemberSignupResponseDto.builder()
                .nickname(member.getNickname())
                .build();
    }

    public MemberLoginResponseDto login(final MemberLoginRequestDto requestDto) throws JWTCreationException {
        final Member member = memberDao.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new MemberException(MemberStatus.NOT_EXISTING_EMAIL)
        );

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new MemberException(MemberStatus.INCORRECT_PASSWORD);
        }

        final Map<String, String> payload = new HashMap<>();

        payload.put("nickname", member.getNickname());

        final Map<String, String> refreshPayload = new HashMap<>();

        refreshPayload.put("studentId", member.getNickname());

        return MemberLoginResponseDto.builder()
                .nickname(member.getNickname())
                .accessToken(accessTokenProvider.generate(payload))
                .refreshToken(refreshTokenProvider.generate(refreshPayload))
                .build();
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
        helper.setText("This is your verification code: " + verification.getCode());

        mailSender.send(message);

        return code;
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
}