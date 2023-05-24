package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.domain.Place;
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

    @PostMapping("/{placeName}")
    public ResponseEntity<?> subscribePlace(@RequestParam final int id, @PathVariable final String placeName) {
        service.subscribePlace(id, placeName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> findByMemberId(@RequestParam int memberId) {
        List<Place> responseDtos = service.findByMembmerId(memberId);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/{placeName}")
    public ResponseEntity<?> findByPlaceId(@PathVariable final String placeName) {
        Place place = service.findByPlaceName(placeName);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @DeleteMapping("/{placeName}")
    public ResponseEntity<?> unsubscribePlace(@RequestParam final int id,
                                              @PathVariable final String placeName) {
        service.unsubscribePlace(id, placeName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
