package site.recofit.ssafit.service;

import site.recofit.ssafit.domain.Place;
import site.recofit.ssafit.dto.place.PlaceListResponseDto;

public interface PlaceService {
    // 장소 커뮤니티 등록
    void registPlace(final Place place);

    // 예약 장소 확인
    Place findByPlaceId(final int placeId);

    // 장소 상세 확인
    Place findByPlaceName(final String placeName);
}
