package yougboyclub.honbabstop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EnableJpaAuditing // AuditingEntityListener를 사용하기 위한 어노테이션.(BaseEntity - regDate 값 생성)
public class HonbabstopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HonbabstopApplication.class, args);
	}
}