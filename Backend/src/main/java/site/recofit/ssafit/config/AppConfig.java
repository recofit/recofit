package site.recofit.ssafit.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import site.recofit.ssafit.properties.GmailProperties;
import site.recofit.ssafit.properties.aws.AwsProperties;
import site.recofit.ssafit.properties.aws.AwsS3Properties;
import site.recofit.ssafit.utility.aws.AwsS3Manager;

import java.util.Properties;

@Configuration
public class AppConfig {
    @Bean
    public JavaMailSender getJavaMailSender(GmailProperties gmailProperties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(gmailProperties.getUsername());
        mailSender.setPassword(gmailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public AmazonS3 amazonS3(final AwsProperties awsProperties, final AwsS3Properties awsS3Properties) {
        final AWSCredentials credentials = new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey());

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsS3Properties.getRegion())
                .build();
    }

    @Bean
    public AwsS3Manager awsS3Manager(final AmazonS3 amazonS3, final AwsS3Properties awsS3Properties) {
        return new AwsS3Manager(amazonS3, awsS3Properties.getBucket());
    }
}