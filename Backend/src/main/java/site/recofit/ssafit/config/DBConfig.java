package site.recofit.ssafit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "site.recofit.ssafit.dao")
public class DBConfig {

}