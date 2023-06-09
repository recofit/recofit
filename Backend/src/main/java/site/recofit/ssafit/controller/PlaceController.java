package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.domain.Place;
import site.recofit.ssafit.dto.place.PlaceRateResponseDto;
import site.recofit.ssafit.dto.place.PlaceReadResponseDto;
import site.recofit.ssafit.dto.place.PlaceRegistRequestDto;
import site.recofit.ssafit.dto.place.PlaceVenueReadResponseDto;
import site.recofit.ssafit.service.PlaceService;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping("")
    public ResponseEntity<Void> createPlace(@RequestBody final PlaceRegistRequestDto requestDto) {
        placeService.registPlace(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{placeName}")
    public ResponseEntity<PlaceReadResponseDto> getPlaceByName(@PathVariable final String placeName) {
        final PlaceReadResponseDto responseDto = placeService.getPlaceByName(placeName);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/average/{placeName}")
    public ResponseEntity<PlaceRateResponseDto> getPlaceAverage(@PathVariable final String placeName) {
        final PlaceRateResponseDto responseDto = placeService.getPlaceAverage(placeName);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
