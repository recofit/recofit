package site.recofit.ssafit.serviceimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.recofit.ssafit.dao.MemberDao;
import site.recofit.ssafit.dao.VideoDao;
import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.domain.Video;
import site.recofit.ssafit.dto.video.VideoListResponseDto;
import site.recofit.ssafit.dto.video.VideoSubscribeRequestDto;
import site.recofit.ssafit.dto.video.VideoUnsubscribeRequestDto;
import site.recofit.ssafit.service.VideoService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoDao videoDao;
    private final MemberDao memberDao;

    @Transactional
    public void registVideo(VideoSubscribeRequestDto requestDto) {
        // 해당 동영상이 데이터베이스에 저장되어 있는 경우
        if (videoDao.findById(requestDto.getVideoId()) != null)
            return;

        Video video = Video.builder()
                .id(requestDto.getVideoId())
                .channelName(requestDto.getChannelName())
                .viewCnt(requestDto.getViewCnt())
                .likeCnt(requestDto.getLikeCnt())
                .build();

        videoDao.save(video);
    }

    @Transactional
    public void subscribeVideo(VideoSubscribeRequestDto requestDto) {

        int memberId = requestDto.getMemberId();
        Member member = memberDao.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("no member"));

        String videoId = requestDto.getVideoId();

        videoDao.subscribe(member.getNickname(), videoId);
    }

    @Transactional
    public void unsubscribeVideo(VideoUnsubscribeRequestDto requestDto) {

        int memberId = requestDto.getMemberId();

        Member member = memberDao.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("no member"));

        String videoId = requestDto.getVideoId();

        videoDao.unsubscribe(member.getNickname(), videoId);
    }

    @Override
    public List<VideoListResponseDto> selectSubscribeVideo(String memberId) {

        List<Video> videoList = videoDao.findByMemberId(memberId);
        List<VideoListResponseDto> dtoList = new ArrayList<>();

        for (Video video : videoList) {
            VideoListResponseDto responseDto = VideoListResponseDto.builder()
                    .id(video.getId())
                    .channelName(video.getChannelName())
                    .viewCnt(video.getViewCnt())
                    .likeCnt(video.getLikeCnt())
                    .build();

            dtoList.add(responseDto);
        }

        return dtoList;
    }
}
