package site.recofit.ssafit.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
public class ReservationRegistResponseDto {
    private final LocalDate startDate;
    private final LocalDate endDate;
}
