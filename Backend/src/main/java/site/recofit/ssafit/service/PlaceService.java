package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.place.PlaceRateResponseDto;
import site.recofit.ssafit.dto.place.PlaceReadResponseDto;
import site.recofit.ssafit.dto.place.PlaceRegistRequestDto;
import site.recofit.ssafit.dto.place.PlaceVenueReadResponseDto;

public interface PlaceService {
    // 장소 커뮤니티 등록
    void registPlace(final PlaceRegistRequestDto requestDto);

    // 예약 장소 확인
    PlaceVenueReadResponseDto getPlaceById(final int placeId);

    // 장소 상세 확인
    PlaceReadResponseDto getPlaceByName(final String placeName);

    PlaceRateResponseDto getPlaceAverage(final String placeName);
}
