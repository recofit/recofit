package site.recofit.ssafit.serviceimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.domain.Video;
import site.recofit.ssafit.dto.VideoListRequestDto;
import site.recofit.ssafit.dto.VideoListResponseDto;
import site.recofit.ssafit.dto.VideoSubscribeRequestDto;
import site.recofit.ssafit.dao.VideoDao;
import site.recofit.ssafit.service.VideoService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoDao videoDao;

    @Transactional
    public void registVideo(VideoSubscribeRequestDto requestDto) {
        // 해당 동영상이 데이터베이스에 저장되어 있는 경우
        if (videoDao.findById(requestDto.getVideoId()) != null)
            return;

        Video video = Video.builder()
                .id(requestDto.getVideoId())
                .title(requestDto.getTitle())
                .url(requestDto.getUrl())
                .channelName(requestDto.getChannelName())
                .build();

        videoDao.save(video);
    }

    @Transactional
    public void subscribeVideo(VideoSubscribeRequestDto requestDto) {

        String memberId = requestDto.getMemberId();
        String videoId = requestDto.getVideoId();

        videoDao.subscribe(memberId, videoId);
    }

    @Transactional
    public void unsubscribeVideo(VideoSubscribeRequestDto requestDto) {

        String memberId = requestDto.getMemberId();
        String videoId = requestDto.getVideoId();

        videoDao.unsubscribe(memberId, videoId);
    }

    @Override
    public List<VideoListResponseDto> selectSubscribeVideo(VideoListRequestDto requestDto) {

        String memberId = requestDto.getMemberId();

        List<Video> videoList = videoDao.findByMemberId(memberId);
        List<VideoListResponseDto> dtoList = new ArrayList<>();

        for (Video video : videoList) {
            VideoListResponseDto responseDto = VideoListResponseDto.builder()
                    .title(video.getTitle())
                    .url(video.getUrl())
                    .channelName(video.getChannelName())
                    .build();

            dtoList.add(responseDto);
        }

        return dtoList;
    }
}
