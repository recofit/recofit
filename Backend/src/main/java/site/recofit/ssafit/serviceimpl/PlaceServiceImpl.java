package site.recofit.ssafit.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.PlaceDao;
import site.recofit.ssafit.domain.Place;
import site.recofit.ssafit.dto.place.PlaceRateResponseDto;
import site.recofit.ssafit.dto.place.PlaceReadResponseDto;
import site.recofit.ssafit.dto.place.PlaceRegistRequestDto;
import site.recofit.ssafit.dto.place.PlaceVenueReadResponseDto;
import site.recofit.ssafit.exception.PlaceException;
import site.recofit.ssafit.exception.status.PlaceStatus;
import site.recofit.ssafit.service.PlaceService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceDao placeDao;

    @Transactional
    public void registPlace(PlaceRegistRequestDto requestDto) {
        if (placeDao.findByPlaceName(requestDto.getTitle()).isPresent()) {
            throw new PlaceException(PlaceStatus.DUPLICATED_PLACE_NAME);
        }

        final Place place = Place.builder()
                .title(requestDto.getTitle())
                .venue(requestDto.getVenue())
                .subjectCategory(requestDto.getSubjectCategory())
                .description(requestDto.getDescription())
                .subDescription(requestDto.getSubDescription())
                .reference(requestDto.getReference())
                .source(requestDto.getSource())
                .build();

        placeDao.savePlace(place);
    }

    @Override
    public PlaceVenueReadResponseDto readPlaceById(final int placeId) {
        final Place place = placeDao.findByPlaceId(placeId).orElseThrow(
                () -> new PlaceException(PlaceStatus.NOT_EXISTING_PLACE)
        );

        return PlaceVenueReadResponseDto.builder()
                .venue(place.getVenue())
                .build();
    }

    @Override
    public PlaceReadResponseDto readPlaceByName(final String placeName) {
        final Place place = placeDao.findByPlaceName(placeName).orElseThrow(
                () -> new PlaceException(PlaceStatus.NOT_EXISTING_PLACE)
        );

        return PlaceReadResponseDto.builder()
                .title(place.getTitle())
                .venue(place.getVenue())
                .subjectCategory(place.getSubjectCategory())
                .description(place.getDescription())
                .subDescription(place.getSubDescription())
                .reference(place.getReference())
                .source(place.getSource())
                .build();
    }

    @Override
    public PlaceRateResponseDto readPlaceRate(final String placeName) {
        final Place place = placeDao.findByPlaceNameWithReview(placeName).orElseThrow(
                () -> new PlaceException(PlaceStatus.NOT_EXISTING_PLACE)
        );

        return PlaceRateResponseDto.builder()
                .rate(place.getRate())
                .build();
    }
}
