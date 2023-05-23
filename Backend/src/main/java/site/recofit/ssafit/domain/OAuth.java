package site.recofit.ssafit.domain;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
public class OAuth {
    private int id;
    private String email;
}
