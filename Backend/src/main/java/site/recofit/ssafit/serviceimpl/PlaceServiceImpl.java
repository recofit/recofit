package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.PlaceDao;
import site.recofit.ssafit.domain.Place;
import site.recofit.ssafit.dto.place.PlaceListResponseDto;
import site.recofit.ssafit.dto.place.PlaceRegistRequestDto;
import site.recofit.ssafit.service.PlaceService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceDao placeDao;

    @Transactional
    public void registPlace(PlaceRegistRequestDto requestDto) {
        Place place = Place.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .openTime(requestDto.getOpenTime())
                .closeTime(requestDto.getCloseTime())
                .longitude(requestDto.getLongitude())
                .latitude(requestDto.getLatitude())
                .build();

        placeDao.savePlace(place);
    }

    @Transactional
    public void subscribePlace(int memberId, int placeId) {
        placeDao.subscribePlace(memberId, placeId);
    }

    @Override
    public List<PlaceListResponseDto> findByMembmerId(int memberId) {
        List<Place> placeList = placeDao.findByMemberId(memberId);
        List<PlaceListResponseDto> dtoList = new ArrayList<>();

        for (Place place : placeList) {
            PlaceListResponseDto responseDto = PlaceListResponseDto.builder()
                    .name(place.getName())
                    .address(place.getAddress())
                    .openTime(place.getOpenTime())
                    .closeTime(place.getCloseTime())
                    .longitude(place.getLongitude())
                    .latitude(place.getLatitude())
                    .build();

            dtoList.add(responseDto);
        }

        return dtoList;
    }

    @Override
    public PlaceListResponseDto findByPlaceId(int placeId) {
        Place place = placeDao.findByPlaceId(placeId);

        PlaceListResponseDto responseDto = PlaceListResponseDto.builder()
                .name(place.getName())
                .address(place.getAddress())
                .openTime(place.getOpenTime())
                .closeTime(place.getCloseTime())
                .longitude(place.getLongitude())
                .latitude(place.getLatitude())
                .build();

        return responseDto;
    }

    @Transactional
    public void unsubscribePlace(int memberId, int placeId) {
        placeDao.unsubscribePlace(memberId, placeId);
    }
}
