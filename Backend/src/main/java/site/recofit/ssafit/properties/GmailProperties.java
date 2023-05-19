package site.recofit.ssafit.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "gmail")
@ConstructorBinding
@Getter
@RequiredArgsConstructor
public class GmailProperties {
    private final String username;
    private final String password;
}
