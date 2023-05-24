package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.PlaceDao;
import site.recofit.ssafit.domain.Place;
import site.recofit.ssafit.service.PlaceService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceDao placeDao;

    @Transactional
    public void registPlace(Place place) {
        placeDao.savePlace(place);
    }

    @Transactional
    public void subscribePlace(int memberId, String placeName) {
        placeDao.subscribePlace(memberId, placeName);
    }

    @Override
    public List<Place> findByMembmerId(int memberId) {
        List<Place> placeList = placeDao.findByMemberId(memberId);
        return placeList;
    }

    @Override
    public Place findByPlaceName(String placeName) {
        Place place = placeDao.findByPlaceName(placeName);
        return place;
    }

    @Transactional
    public void unsubscribePlace(int memberId, String placeName) {
        placeDao.unsubscribePlace(memberId, placeName);
    }
}
