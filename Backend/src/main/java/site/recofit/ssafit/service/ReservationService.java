package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.reservation.ReservationReadResponseDto;
import site.recofit.ssafit.dto.reservation.ReservationRegistRequestDto;
import site.recofit.ssafit.dto.reservation.ReservationRegistResponseDto;

import java.util.List;

public interface ReservationService {
    ReservationRegistResponseDto createReservation(final ReservationRegistRequestDto requestDto);

    List<ReservationReadResponseDto> findMemberReservationList(final int memberId);

    List<ReservationReadResponseDto> findPlaceReservationList(final String placeName);

    void cancelReservation(final int memberId, final int placeId);
}
