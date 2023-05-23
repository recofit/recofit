package site.recofit.ssafit.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
public class Member {
    public static final String BASIC_PICTURE = "basic_picture";

    private int id;
    private String email;
    private String password;
    private String nickname;
    private String picture;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
