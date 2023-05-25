package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.domain.Member;
import site.recofit.ssafit.dto.video.VideoListResponseDto;
import site.recofit.ssafit.dto.video.VideoSubscribeRequestDto;
import site.recofit.ssafit.dto.video.VideoUnsubscribeRequestDto;
import site.recofit.ssafit.service.MemberService;
import site.recofit.ssafit.service.VideoService;

import java.util.List;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VideoController {

    private final VideoService videoServiceImpl;

    @PostMapping()
    public ResponseEntity<?> subscribeVideo(@RequestBody final VideoSubscribeRequestDto requestDto) {
        videoServiceImpl.registVideo(requestDto);
        videoServiceImpl.subscribeVideo(requestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<?> unsubscribeVideo(@RequestBody final VideoUnsubscribeRequestDto requestDto) {
        videoServiceImpl.unsubscribeVideo(requestDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<VideoListResponseDto>> findSubscribeVideo(@RequestParam final int memberId) {
        List<VideoListResponseDto> responseDtos = videoServiceImpl.selectSubscribeVideo(memberId);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}