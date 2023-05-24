package site.recofit.ssafit.dao;

import org.springframework.stereotype.Repository;
import site.recofit.ssafit.domain.Place;

import java.util.List;

@Repository
public interface PlaceDao {
    // 장소 커뮤니티 등록
    void savePlace(final Place place);

    // 장소 찜
    void subscribePlace(final int memberId, final String placeName);

    // 장소 찜 목록 확인
    List<Place> findByMemberId(final int memberId);

    // 장소 상세 확인
    Place findByPlaceName(final String placeName);

    // 장소 찜 해제
    void unsubscribePlace(final int memberId, final String placeName);
}
