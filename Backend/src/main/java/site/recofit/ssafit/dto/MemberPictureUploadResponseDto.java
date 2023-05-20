package site.recofit.ssafit.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Builder
public class MemberPictureUploadResponseDto {
    private final String picture;
}
