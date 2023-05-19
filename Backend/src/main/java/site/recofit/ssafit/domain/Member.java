package site.recofit.ssafit.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Member {
    private int id;
    private String email;
    private String password;
    private String nickname;
    private String picture;

    @Builder
    public Member(int id, String email, String password, String nickname, String picture) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.picture = picture;
    }
}
