package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.domain.Reservation;
import site.recofit.ssafit.dto.ReservationRegistRequestDto;
import site.recofit.ssafit.dto.ReservationRegistResponseDto;
import site.recofit.ssafit.security.userdetails.MemberDetails;
import site.recofit.ssafit.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/write")
    public ResponseEntity<ReservationRegistResponseDto> createReservation(@AuthenticationPrincipal final MemberDetails memberDetails,
                                                          @RequestBody final ReservationRegistRequestDto requestDto) {
        final int memberId = memberDetails.getId();

        ReservationRegistResponseDto responseDto = reservationService.createReservation(memberId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("")
    public ResponseEntity<List<Reservation>> findMemberUnavailableDate(@AuthenticationPrincipal final MemberDetails memberDetails) {
        final int memberId = memberDetails.getId();

        List<Reservation> list = reservationService.findMemberUnavailableDate(memberId);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<List<Reservation>> findMemberUnavailableDate(@PathVariable final int placeId) {
        List<Reservation> list = reservationService.findPlaceUnavailableDate(placeId);

        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> cancelReservation(@AuthenticationPrincipal final MemberDetails memberDetails,
                                                  @PathVariable final int placeId) {
        final int memberId = memberDetails.getId();

        reservationService.cancelReservation(memberId, placeId);

        return ResponseEntity.ok().build();
    }
}
