package site.recofit.ssafit.repository;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.Member;

@Repository
public interface MemberDao {
    Member save(final Member member);
    Member findByEmail(final String email);
}
