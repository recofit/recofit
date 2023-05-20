package site.recofit.ssafit.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    private int id;
    private int memberId;
    private int placeId;
    private String title;
    private String content;
    private String picture;
    private int rate;
    private int likeCnt;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
