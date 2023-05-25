package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.PlaceDao;
import site.recofit.ssafit.domain.Place;
import site.recofit.ssafit.service.PlaceService;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceDao placeDao;

    @Transactional
    public void registPlace(Place place) {
        if (placeDao.findByPlaceName(place.getTitle()).isEmpty()) {
            placeDao.savePlace(place);
        }
    }

    @Override
    public Place findByPlaceId(final int placeId) {
        Place place = placeDao.findByPlaceId(placeId);
        return place;
    }

    @Override
    public Optional<Place> findByPlaceName(String placeName) {
        Optional<Place> place = placeDao.findByPlaceName(placeName);

        return place;
    }

    @Override
    public Optional<Place> findByPlaceNameWithReview(String placeName) {
        Optional<Place> place = placeDao.findByPlaceNameWithReview(placeName);

        return place;
    }
}
