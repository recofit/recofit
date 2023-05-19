package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.dto.MemberSignupRequestDto;
import site.recofit.ssafit.dto.MemberSignupResponseDto;
import site.recofit.ssafit.repository.MemberDao;
import site.recofit.ssafit.service.MemberService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    @Transactional
    public MemberSignupResponseDto signup(MemberSignupRequestDto requestDto) {
        if (memberDao.findByEmail(requestDto.getEmail()) != null)
            throw new IllegalArgumentException("중복된 이메일입니다.");

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .nickname(requestDto.getNickname())
                .picture(requestDto.getPicture())
                .build();

        memberDao.save(member);

        return MemberSignupResponseDto.builder()
                .nickname(member.getNickname())
                .build();
    }
}
