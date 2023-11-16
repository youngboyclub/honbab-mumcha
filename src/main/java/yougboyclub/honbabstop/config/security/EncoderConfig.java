package yougboyclub.honbabstop.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * DB에 정보가 저장될 때 인코딩을 하여 암호화 시켜서
 * DB에 접근하여도 알 수 없게끔 하는 역할.
 */
@Configuration
public class EncoderConfig {

    //순환참조 문제 방지 ??
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
