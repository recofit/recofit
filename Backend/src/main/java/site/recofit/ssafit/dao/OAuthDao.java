package site.recofit.ssafit.dao;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.OAuth;

import java.util.Optional;

@Repository
public interface OAuthDao {
    void save(final OAuth oAuth);
    Optional<OAuth> findByEmail(final String email);
}
