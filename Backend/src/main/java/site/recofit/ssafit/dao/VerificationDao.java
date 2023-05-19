package site.recofit.ssafit.dao;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.Verification;

import java.util.Optional;

@Repository
public interface VerificationDao {
    void save(final Verification verification);

    Optional<Verification> findByCode(final String code);

    Optional<Verification> findByEmail(final String email);

    void verify(final String email);

    void unverify(final String email);

    void refreshCode(final String code, final String email);
}
