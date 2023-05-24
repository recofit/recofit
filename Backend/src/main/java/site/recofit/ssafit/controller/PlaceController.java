package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.place.PlaceListResponseDto;
import site.recofit.ssafit.dto.place.PlaceRegistRequestDto;
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
    public ResponseEntity<?> subscribePlace(
            @RequestParam int id,
                                             @PathVariable final int placeId) {
        service.subscribePlace(id, placeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> findByMemberId(@RequestParam int id) {
        List<PlaceListResponseDto> responseDtos = service.findByMembmerId(id);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<?> findByPlaceId(@PathVariable final int placeId) {
        PlaceListResponseDto responseDto = service.findByPlaceId(placeId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<?> unsubscribePlace(@RequestParam int id,
                                              @PathVariable final int placeId) {
        service.unsubscribePlace(id, placeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
