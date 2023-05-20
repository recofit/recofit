package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.ReservationDao;
import site.recofit.ssafit.domain.Reservation;
import site.recofit.ssafit.dto.ReservationRegistRequestDto;
import site.recofit.ssafit.dto.ReservationRegistResponseDto;
import site.recofit.ssafit.service.ReservationService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao;

    @Transactional
    public ReservationRegistResponseDto createReservation(final int memberId,
                                                       final ReservationRegistRequestDto requestDto) {
        // 날짜 가능한지에 대한 유효성 검사 필요여부 차후 판단

        Reservation reservation = Reservation.builder()
                .memberId(memberId)
                .placeId(requestDto.getPlaceId())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .build();

        reservationDao.save(reservation);

        return ReservationRegistResponseDto.builder()
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

    public List<Reservation> findMemberUnavailableDate(final int memberId) {
        return reservationDao.findByMemberId(memberId);
    }

    public List<Reservation> findPlaceUnavailableDate(final int placeId) {
        return reservationDao.findByPlaceId(placeId);
    }

    public void cancelReservation(final int memberId, final int placeId) {
        reservationDao.deleteByMemberIdAndPlaceId(memberId, placeId);
    }
}
