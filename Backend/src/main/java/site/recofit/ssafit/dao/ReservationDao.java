package site.recofit.ssafit.dao;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.Reservation;

import java.util.List;

@Repository
public interface ReservationDao {
    void save(final Reservation reservation);

    List<Reservation> findByMemberId(final int memberId);

    List<Reservation> findByPlaceId(final int placeId);

    void deleteByMemberIdAndPlaceId(final int memberId, final int placeId);
}
