package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.reservation.ReservationReadResponseDto;
import site.recofit.ssafit.dto.reservation.ReservationRegistRequestDto;
import site.recofit.ssafit.dto.reservation.ReservationRegistResponseDto;
import site.recofit.ssafit.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/write")
    public ResponseEntity<ReservationRegistResponseDto> createReservation(@RequestParam int memberId,
                                                          @RequestBody final ReservationRegistRequestDto requestDto) {

        ReservationRegistResponseDto responseDto = reservationService.createReservation(memberId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("")
    public ResponseEntity<List<ReservationReadResponseDto>> findMemberUnavailableDate(@RequestParam int memberId) {

        List<ReservationReadResponseDto> list = reservationService.findMemberUnavailableDate(memberId);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<List<ReservationReadResponseDto>> findPlaceUnavailableDate(@PathVariable final int placeId) {
        List<ReservationReadResponseDto> list = reservationService.findPlaceUnavailableDate(placeId);

        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> cancelReservation(@RequestParam int id,
                                                  @PathVariable final int placeId) {

        reservationService.cancelReservation(id, placeId);

        return ResponseEntity.ok().build();
    }
}
