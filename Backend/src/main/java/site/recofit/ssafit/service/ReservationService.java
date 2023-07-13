package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.reservation.ReservationReadResponseDto;
import site.recofit.ssafit.dto.reservation.ReservationRegistRequestDto;

import java.util.List;

public interface ReservationService {
    void createReservation(final ReservationRegistRequestDto requestDto);

    List<ReservationReadResponseDto> getMemberReservationList(final int memberId);

    List<ReservationReadResponseDto> getPlaceReservationList(final String placeName);

    void cancelReservation(final int memberId, final int placeId);
}
