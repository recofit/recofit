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
import site.recofit.ssafit.exception.MemberException;
import site.recofit.ssafit.exception.VideoException;
import site.recofit.ssafit.exception.status.MemberStatus;
import site.recofit.ssafit.exception.status.VideoStatus;
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
    public void registVideo(final VideoSubscribeRequestDto requestDto) {
        if (videoDao.findById(requestDto.getVideoId()).isPresent()) {
            throw new VideoException(VideoStatus.EXISTING_VIDEO);
        }

        final Video video = Video.builder()
                .id(requestDto.getVideoId())
                .channelName(requestDto.getChannelName())
                .viewCnt(requestDto.getViewCnt())
                .likeCnt(requestDto.getLikeCnt())
                .build();

        videoDao.save(video);
    }

    @Transactional
    public void subscribeVideo(final VideoSubscribeRequestDto requestDto) {
        final int memberId = requestDto.getMemberId();

        final Member member = memberDao.findById(memberId).orElseThrow(
                () -> new MemberException(MemberStatus.NOT_EXISTING_MEMBER)
        );

        final String videoId = requestDto.getVideoId();

        videoDao.subscribe(member.getNickname(), videoId);
    }

    @Transactional
    public void unsubscribeVideo(final VideoUnsubscribeRequestDto requestDto) {
        final int memberId = requestDto.getMemberId();

        final Member member = memberDao.findById(memberId).orElseThrow(
                () -> new MemberException(MemberStatus.NOT_EXISTING_MEMBER)
        );

        final String videoId = requestDto.getVideoId();

        videoDao.unsubscribe(member.getNickname(), videoId);
    }

    @Override
    public List<VideoListResponseDto> getSubscribeVideo(final int memberId) {
        final Member member = memberDao.findById(memberId).orElseThrow(
                () -> new MemberException(MemberStatus.NOT_EXISTING_MEMBER)
        );

        final String memberName = member.getNickname();

        final List<Video> videoList = videoDao.findByMemberId(memberName);

        final List<VideoListResponseDto> dtoList = new ArrayList<>();

        for (final Video video : videoList) {
            final VideoListResponseDto responseDto = VideoListResponseDto.builder()
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
