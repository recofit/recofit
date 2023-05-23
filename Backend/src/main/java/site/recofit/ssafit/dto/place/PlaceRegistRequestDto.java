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
    private String name;
    private String address;
    private String openTime;
    private String closeTime;
    private String longitude;
    private String latitude;
}
