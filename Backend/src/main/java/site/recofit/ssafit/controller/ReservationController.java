package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.reservation.ReservationReadResponseDto;
import site.recofit.ssafit.dto.reservation.ReservationRegistRequestDto;
import site.recofit.ssafit.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/write")
    public ResponseEntity<Void> createReservation(@RequestBody final ReservationRegistRequestDto requestDto) {
        reservationService.createReservation(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<ReservationReadResponseDto>> getMemberReservationList(@RequestParam final int memberId) {
        final List<ReservationReadResponseDto> list = reservationService.getMemberReservationList(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{placeName}")
    public ResponseEntity<List<ReservationReadResponseDto>> getPlaceReservationList(@PathVariable final String placeName) {
        final List<ReservationReadResponseDto> list = reservationService.getPlaceReservationList(placeName);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> cancelReservation(@RequestParam final int id,
                                                  @PathVariable final int placeId) {
        reservationService.cancelReservation(id, placeId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
