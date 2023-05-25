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
import site.recofit.ssafit.dto.reservation.ReservationRegistResponseDto;
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
    public ReservationRegistResponseDto createReservation(final ReservationRegistRequestDto requestDto) {
        // 날짜 가능한지에 대한 유효성 검사 필요여부 차후 판단

        Place place = placeDao.findByPlaceName(requestDto.getPlaceName()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 장소가 없습니다.")
        );

        Reservation reservation = Reservation.builder()
                .memberId(requestDto.getMemberId())
                .placeId(place.getId())
                .startDate(requestDto.getStart())
                .endDate(requestDto.getEnd())
                .build();

        reservationDao.save(reservation);

        return ReservationRegistResponseDto.builder()
                .name(place.getTitle())
                .build();
    }

    public List<ReservationReadResponseDto> findMemberReservationList(final int memberId) {
        List<Reservation> reservationList = reservationDao.findByMemberId(memberId);

        return getReservationDtoList(reservationList);
    }

    public List<ReservationReadResponseDto> findPlaceReservationList(final String placeName) {
        Place place = placeDao.findByPlaceName(placeName).orElseThrow(
                () -> new IllegalArgumentException("해당하는 장소가 없습니다.")
        );

        List<Reservation> reservationList = reservationDao.findByPlaceId(place.getId());

        return getReservationDtoList(reservationList);
    }

    public void cancelReservation(final int memberId, final int placeId) {
        reservationDao.deleteByMemberIdAndPlaceId(memberId, placeId);
    }

    private List<ReservationReadResponseDto> getReservationDtoList(final List<Reservation> reservationList) {
        List<ReservationReadResponseDto> dtoList = new ArrayList<>();

        for (Reservation reservation : reservationList) {
            Place place = placeDao.findByPlaceId(reservation.getPlaceId());

            ReservationReadResponseDto responseDto;

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
