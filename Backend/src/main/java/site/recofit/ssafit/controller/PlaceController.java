package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.domain.Place;
import site.recofit.ssafit.dto.place.PlaceListResponseDto;
import site.recofit.ssafit.service.PlaceService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService service;

    @PostMapping("")
    public ResponseEntity<?> registPlace(@RequestBody final Place place) {
        if (service.findByPlaceName(place.getTitle()) != null)
            return new ResponseEntity<Void>(HttpStatus.ALREADY_REPORTED);

        service.registPlace(place);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/{placeName}")
    public ResponseEntity<?> findByPlaceId(@PathVariable final String placeName) {
        Place place = service.findByPlaceName(placeName);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }
}
