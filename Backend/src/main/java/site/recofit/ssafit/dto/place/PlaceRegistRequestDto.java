package site.recofit.ssafit.dto.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaceRegistRequestDto {
    private String title;
    private String venue;
    private String subjectCategory;
    private String description;
    private String subDescription;
    private String reference;
    private String source;
}
