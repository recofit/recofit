package site.recofit.ssafit.dto.video;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoListResponseDto {
    private String id;
    private String channelName;
    private int viewCnt;
    private int likeCnt;
}
