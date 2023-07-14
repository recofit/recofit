package site.recofit.ssafit.dto.member;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class MemberPictureUploadRequestDto {
    private final MultipartFile pictureFile;
}
