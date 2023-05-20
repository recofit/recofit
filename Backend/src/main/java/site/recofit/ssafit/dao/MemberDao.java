package site.recofit.ssafit.dao;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberDao {
    void save(final Member member);

    Optional<Member> findByEmail(final String email);

    Optional<Member> findByNickname(final String nickname);

    Optional<Member> findById(final int id);

    void follow(final int followerId, final int followingId);

    void unfollow(final int followerId, final int followingId);

    boolean findByFollowerIdAndFollowingId(final int followerId, final int followingId);

    List<Integer> findByFollowingId(final int followingId);

    List<Integer> findByFollowerId(final int followerId);

    void updatePicture(final String url, final String email);

    void updateNickname(final String nickname, final String email);
}
