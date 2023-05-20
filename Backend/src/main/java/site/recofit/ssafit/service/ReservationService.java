package site.recofit.ssafit.service;

import site.recofit.ssafit.domain.Reservation;
import site.recofit.ssafit.dto.ReservationRegistRequestDto;
import site.recofit.ssafit.dto.ReservationRegistResponseDto;

import java.util.List;

public interface ReservationService {
    ReservationRegistResponseDto createReservation(final int memberId, final ReservationRegistRequestDto requestDto);

    List<Reservation> findMemberUnavailableDate(final int memberId);

    List<Reservation> findPlaceUnavailableDate(final int placeId);

    void cancelReservation(final int memberId, final int placeId);
}
