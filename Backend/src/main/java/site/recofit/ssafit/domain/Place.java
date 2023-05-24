package site.recofit.ssafit.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Place {
    private int id;
    private String title;
    private String venue;
    private String subjectCategory;
    private String description;
    private String subDescription;
    private String reference;
    private String source;
    private double rate;
}
