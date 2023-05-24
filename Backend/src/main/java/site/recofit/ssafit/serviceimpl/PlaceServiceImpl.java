package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.PlaceDao;
import site.recofit.ssafit.domain.Place;
import site.recofit.ssafit.service.PlaceService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceDao placeDao;

    @Transactional
    public void registPlace(Place place) {
        placeDao.savePlace(place);
    }

    @Override
    public Place findByPlaceId(final int placeId) {
        Place place = placeDao.findByPlaceId(placeId);
        return place;
    }

    @Override
    public Place findByPlaceName(String placeName) {
        Place place = placeDao.findByPlaceName(placeName);
        return place;
    }
}
