package site.recofit.ssafit.dto.place;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class PlaceRateResponseDto {
    private final double rate;
}
