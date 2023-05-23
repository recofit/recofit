package site.recofit.ssafit.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewUpdateRequestDto {
    private String title;
    private String content;
    private String picture;
    private int rate;
}
