package site.recofit.ssafit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceListResponseDto {
    private String name;
    private String address;
    private String openTime;
    private String closeTime;
    private String longitude;
    private String latitude;
}
