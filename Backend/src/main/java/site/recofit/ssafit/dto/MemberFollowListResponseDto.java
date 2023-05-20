package site.recofit.ssafit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class MemberFollowListResponseDto {
    private final int id;
    private final String nickname;
}
