package site.recofit.ssafit.dto.reservation;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class ReservationReadResponseDto {
    private final String title;
    private final String venue;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
