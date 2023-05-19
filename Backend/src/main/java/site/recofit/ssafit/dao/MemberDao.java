package site.recofit.ssafit.dao;

import site.recofit.ssafit.domain.Member;

import java.util.Optional;

public interface MemberDao {
    void save(final Member member);
    Optional<Member> findByEmail(final String email);
    Optional<Member> findByNickname(final String nickname);
}
