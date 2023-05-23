package site.recofit.ssafit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.recofit.ssafit.dto.video.VideoListRequestDto;
import site.recofit.ssafit.dto.video.VideoListResponseDto;
import site.recofit.ssafit.dto.video.VideoSubscribeRequestDto;
import site.recofit.ssafit.service.VideoService;

import java.util.List;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoServiceImpl;

    @PostMapping()
    public ResponseEntity<?> subscribeVideo(@RequestBody final VideoSubscribeRequestDto requestDto) {
        videoServiceImpl.registVideo(requestDto);
        videoServiceImpl.subscribeVideo(requestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<?> unsubscribeVideo(@RequestBody final VideoSubscribeRequestDto requestDto) {
        videoServiceImpl.unsubscribeVideo(requestDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<VideoListResponseDto>> findSubscribeVideo(@RequestBody final VideoListRequestDto requestDto) {
        List<VideoListResponseDto> responseDtos = videoServiceImpl.selectSubscribeVideo(requestDto);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}