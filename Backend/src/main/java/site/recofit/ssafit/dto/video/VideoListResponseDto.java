package site.recofit.ssafit.dto.video;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoListResponseDto {
    private String title;
    private String url;
    private String channelName;
}
