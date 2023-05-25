package site.recofit.ssafit.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class MemberFollowListResponseDto {
    private final int id;
    private final String nickname;
    private final String picture;
}
