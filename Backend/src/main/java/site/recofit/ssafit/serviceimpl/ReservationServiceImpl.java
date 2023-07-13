package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.PlaceDao;
import site.recofit.ssafit.dao.ReservationDao;
import site.recofit.ssafit.domain.Place;
import site.recofit.ssafit.domain.Reservation;
import site.recofit.ssafit.dto.reservation.ReservationReadResponseDto;
import site.recofit.ssafit.dto.reservation.ReservationRegistRequestDto;
import site.recofit.ssafit.exception.PlaceException;
import site.recofit.ssafit.exception.status.PlaceStatus;
import site.recofit.ssafit.service.ReservationService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao;
    private final PlaceDao placeDao;

    @Transactional
    public void createReservation(final ReservationRegistRequestDto requestDto) {
        // 날짜 가능한지에 대한 유효성 검사 필요여부 차후 판단
        final Place place = placeDao.findByPlaceName(requestDto.getPlaceName()).orElseThrow(
                () -> new PlaceException(PlaceStatus.NOT_EXISTING_PLACE)
        );

        final Reservation reservation = Reservation.builder()
                .memberId(requestDto.getMemberId())
                .placeId(place.getId())
                .startDate(requestDto.getStart())
                .endDate(requestDto.getEnd())
                .build();

        reservationDao.save(reservation);
    }

    public List<ReservationReadResponseDto> getMemberReservationList(final int memberId) {
        final List<Reservation> reservationList = reservationDao.findByMemberId(memberId);

        return getReservationDtoList(reservationList);
    }

    public List<ReservationReadResponseDto> getPlaceReservationList(final String placeName) {
        final Place place = placeDao.findByPlaceName(placeName).orElseThrow(
                () -> new PlaceException(PlaceStatus.NOT_EXISTING_PLACE)
        );

        final List<Reservation> reservationList = reservationDao.findByPlaceId(place.getId());

        return getReservationDtoList(reservationList);
    }

    public void cancelReservation(final int memberId, final int placeId) {
        reservationDao.deleteByMemberIdAndPlaceId(memberId, placeId);
    }

    private List<ReservationReadResponseDto> getReservationDtoList(final List<Reservation> reservationList) {
        final List<ReservationReadResponseDto> dtoList = new ArrayList<>();

        for (final Reservation reservation : reservationList) {
            final Place place = placeDao.findByPlaceId(reservation.getPlaceId()).orElse(null);

            final ReservationReadResponseDto responseDto;

            if (place == null) {
                responseDto = ReservationReadResponseDto.builder()
                        .startDate(reservation.getStartDate())
                        .endDate(reservation.getEndDate())
                        .build();
            } else {
                responseDto = ReservationReadResponseDto.builder()
                        .title(place.getTitle())
                        .venue(place.getVenue())
                        .startDate(reservation.getStartDate())
                        .endDate(reservation.getEndDate())
                        .build();
            }

            dtoList.add(responseDto);
        }

        return dtoList;
    }
}
