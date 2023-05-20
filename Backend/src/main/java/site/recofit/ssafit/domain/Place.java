package site.recofit.ssafit.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Place {
    private int id;
    private String name;
    private String address;
    private String openTime;
    private String closeTime;
    private String longitude;
    private String latitude;
    private String createdDate;
    private String lastModifiedDate;
}
