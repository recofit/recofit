package site.recofit.ssafit.service;

import site.recofit.ssafit.domain.Place;

import java.util.List;

public interface PlaceService {
    // 장소 커뮤니티 등록
    void registPlace(final Place place);

    // 장소 찜
    void subscribePlace(final int memberId, final String placeName);

    // 장소 찜 목록 확인
    List<Place> findByMembmerId(final int memberId);

    // 장소 상세 확인
    Place findByPlaceName(final String placeName);

    // 장소 찜 해제
    void unsubscribePlace(final int memberId, final String placeName);
}
