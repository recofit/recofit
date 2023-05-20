package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.PlaceListResponseDto;
import site.recofit.ssafit.dto.PlaceRegistRequestDto;

import java.util.List;

public interface PlaceService {
    // 장소 커뮤니티 등록
    void registPlace(final PlaceRegistRequestDto requestDto);

    // 장소 찜
    void subscribePlace(final int memberId, final int placeId);

    // 장소 찜 목록 확인
    List<PlaceListResponseDto> findByMembmerId(final int memberId);

    // 장소 상세 확인
    PlaceListResponseDto findByPlaceId(final int placeId);

    // 장소 찜 해제
    void unsubscribePlace(final int memberId, final int placeId);
}
