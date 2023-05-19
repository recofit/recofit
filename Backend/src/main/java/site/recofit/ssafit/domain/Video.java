package site.recofit.ssafit.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Video {
    private String id;
    private String title;
    private String url;
    private String channelName;
}
