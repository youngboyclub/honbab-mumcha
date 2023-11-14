package yougboyclub.honbabstop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // 클라이언트 애플리케이션의 주소를 여기에 입력하세요.
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
        ;
    }
}