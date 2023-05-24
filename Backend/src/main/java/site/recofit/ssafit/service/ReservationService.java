package site.recofit.ssafit.service;

import site.recofit.ssafit.domain.Reservation;
import site.recofit.ssafit.dto.reservation.ReservationReadResponseDto;
import site.recofit.ssafit.dto.reservation.ReservationRegistRequestDto;
import site.recofit.ssafit.dto.reservation.ReservationRegistResponseDto;

import java.util.List;

public interface ReservationService {
    ReservationRegistResponseDto createReservation(final int memberId, final ReservationRegistRequestDto requestDto);

    List<ReservationReadResponseDto> findMemberUnavailableDate(final int memberId);

    List<ReservationReadResponseDto> findPlaceUnavailableDate(final int placeId);

    void cancelReservation(final int memberId, final int placeId);
}
