package site.recofit.ssafit.dao;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.Place;

@Repository
public interface PlaceDao {
    // 장소 커뮤니티 등록
    void savePlace(final Place place);

    // 예약 장소 확인
    Place findByPlaceId(final int placeId);

    // 장소 상세 확인
    Place findByPlaceName(final String placeName);
}
