package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.video.VideoListRequestDto;
import site.recofit.ssafit.dto.video.VideoListResponseDto;
import site.recofit.ssafit.dto.video.VideoSubscribeRequestDto;

import java.util.List;

public interface VideoService {
    void registVideo(final VideoSubscribeRequestDto requestDto);

    void subscribeVideo(final VideoSubscribeRequestDto requestDto);

    void unsubscribeVideo(final VideoSubscribeRequestDto requestDto);

    List<VideoListResponseDto> selectSubscribeVideo(final VideoListRequestDto requestDto);
}
