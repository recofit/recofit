package site.recofit.ssafit.dto.member;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Builder
@Setter
public class MemberPictureUploadRequestDto {
    private final MultipartFile pictureFile;
}
