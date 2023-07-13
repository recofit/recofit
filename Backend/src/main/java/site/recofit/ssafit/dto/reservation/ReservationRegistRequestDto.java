package site.recofit.ssafit.dto.reservation;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class ReservationRegistRequestDto {
    private final int memberId;
    private final String placeName;
    private final LocalDate start;
    private final LocalDate end;
}
