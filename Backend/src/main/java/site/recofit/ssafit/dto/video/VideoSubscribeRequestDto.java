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
    private int memberId;
    private String videoId;
    private String channelName;
    private int viewCnt;
    private int likeCnt;
}
