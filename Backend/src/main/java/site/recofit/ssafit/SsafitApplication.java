package site.recofit.ssafit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import site.recofit.ssafit.properties.GmailProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		GmailProperties.class
})
public class SsafitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsafitApplication.class, args);
	}

}
