package site.recofit.ssafit.dto.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VideoSubscribeRequestDto {
    private String memberId;
    private String videoId;
    private String title;
    private String url;
    private String channelName;
}
