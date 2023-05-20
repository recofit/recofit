package site.recofit.ssafit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewRegistRequestDto {
    private int memberId;
    private int placeId;
    private String title;
    private String content;
    private String picture;
    private int rate;
}
