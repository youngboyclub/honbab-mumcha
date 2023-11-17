package yougboyclub.honbabstop.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import yougboyclub.honbabstop.config.filter.JwtAuthencationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록됨.
public class WebSecurityConfig {

    private final JwtAuthencationFilter jwtAuthencationFilter;

    @Bean
    protected SecurityFilterChain configure (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //cors정책 (현재 application에서 작업 해둠. => 기본값 사용)
                .cors().and()
                //csrf 대책 (현재는 csrf 비활성화)
                .csrf().disable()
                //basic 인증 (현재는 Bearer token을 사용하기 때문에 비활성화)
                .httpBasic().disable()
                // 세션 기반 인증 (현재는 session 기반 인증을 사용하지 않음.)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/users/new", "/api/users/login", "/api/users/emails/**").permitAll()
                .antMatchers("/api/board/new", "/api/board/boardDetails/**","/api/my/**").authenticated()
                .anyRequest().permitAll();

        httpSecurity.addFilterBefore(jwtAuthencationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
