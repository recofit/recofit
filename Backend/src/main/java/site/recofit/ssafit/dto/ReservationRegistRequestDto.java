package site.recofit.ssafit.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
@Builder
@RequiredArgsConstructor
public class ReservationRegistRequestDto {
    private final int placeId;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
