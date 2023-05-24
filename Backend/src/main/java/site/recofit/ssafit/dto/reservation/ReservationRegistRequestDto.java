package site.recofit.ssafit.dto.reservation;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ReservationRegistRequestDto {
    private final int memberId;
    private final String placeName;
    private final LocalDate start;
    private final LocalDate end;
}
