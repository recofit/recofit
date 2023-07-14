package site.recofit.ssafit.service;

import site.recofit.ssafit.dto.video.VideoListResponseDto;
import site.recofit.ssafit.dto.video.VideoSubscribeRequestDto;
import site.recofit.ssafit.dto.video.VideoUnsubscribeRequestDto;

import java.util.List;

public interface VideoService {
    void registVideo(final VideoSubscribeRequestDto requestDto);

    void subscribeVideo(final VideoSubscribeRequestDto requestDto);

    void unsubscribeVideo(final VideoUnsubscribeRequestDto requestDto);

    List<VideoListResponseDto> getSubscribeVideo(final int memberId);
}
