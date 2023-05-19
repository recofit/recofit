package site.recofit.ssafit.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Getter
@Builder
public class Verification {
    private Long id;
    private String email;
    private String code;
    private boolean state;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public String refreshCode() {
        String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder randomString = new StringBuilder(6);
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(alphanumericCharacters.length());
            char randomChar = alphanumericCharacters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
