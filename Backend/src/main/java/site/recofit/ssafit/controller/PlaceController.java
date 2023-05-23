package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.place.PlaceListResponseDto;
import site.recofit.ssafit.dto.place.PlaceRegistRequestDto;
import site.recofit.ssafit.security.userdetails.MemberDetails;
import site.recofit.ssafit.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService service;

    @PostMapping("/{latitude}/{longitude}")
    public ResponseEntity<?> registPlace(@RequestBody final PlaceRegistRequestDto requestDto) {
        service.registPlace(requestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/{placeId}")
    public ResponseEntity<?> subscribePlace(@AuthenticationPrincipal final MemberDetails memberDetails,
                                             @PathVariable final int placeId) {
        service.subscribePlace(memberDetails.getId(), placeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> findByMemberId(@AuthenticationPrincipal final MemberDetails memberDetails) {
        List<PlaceListResponseDto> responseDtos = service.findByMembmerId(memberDetails.getId());
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<?> findByPlaceId(@PathVariable final int placeId) {
        PlaceListResponseDto responseDto = service.findByPlaceId(placeId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<?> unsubscribePlace(@AuthenticationPrincipal final MemberDetails memberDetails,
                                              @PathVariable final int placeId) {
        service.unsubscribePlace(memberDetails.getId(), placeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
