package site.recofit.ssafit.dto.member;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class MemberLoginRequestDto {
    private final String email;
    private final String password;
}