package site.recofit.ssafit.dto;

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
