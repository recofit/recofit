package site.recofit.ssafit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.recofit.ssafit.exception.status.VideoStatus;

@RequiredArgsConstructor
@Getter
public class VideoException extends RuntimeException{
    private final VideoStatus status;
}
