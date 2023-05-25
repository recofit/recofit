package site.recofit.ssafit.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Video {
    private String id;
    private String channelName;
    private int viewCnt;
    private int likeCnt;
}
