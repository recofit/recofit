package site.recofit.ssafit.dto.reservation;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
public class ReservationReadResponseDto {
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
