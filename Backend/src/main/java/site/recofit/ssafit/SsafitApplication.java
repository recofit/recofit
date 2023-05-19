package site.recofit.ssafit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import site.recofit.ssafit.properties.GmailProperties;
import site.recofit.ssafit.properties.jwt.AccessTokenProperties;
import site.recofit.ssafit.properties.jwt.RefreshTokenProperties;
import site.recofit.ssafit.properties.security.SecurityCorsProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		GmailProperties.class,
		AccessTokenProperties.class,
		RefreshTokenProperties.class,
		SecurityCorsProperties.class
})
public class SsafitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsafitApplication.class, args);
	}

}
