package site.recofit.ssafit.dto.review;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewListResponseDto {
    private int id;
    private String title;
    private String name;
    private String content;
    private String picture;
    private int rate;
    private int likeCnt;
    private LocalDateTime lastModifiedDate;
}
