package site.recofit.ssafit.dto.place;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class PlaceReadResponseDto {
    private final String title;
    private final String venue;
    private final String subjectCategory;
    private final String description;
    private final String subDescription;
    private final String reference;
    private final String source;
}
