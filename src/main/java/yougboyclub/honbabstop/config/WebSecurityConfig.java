package yougboyclub.honbabstop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import yougboyclub.honbabstop.service.UserDetailCustomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록됨.
public class WebSecurityConfig {

    private final UserDetailCustomService userService; //UserDetailCustomService 주입

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/api/users/new", "/api/users/login").permitAll()
                                //.antMatchers("/board/**").authenticated()  //-> 작업 시 이거 주석해제 하고 작업하시면 됨.
                                .anyRequest().permitAll())

                .exceptionHandling().authenticationEntryPoint(CustomAuthenticationEntryPoint());
        return http.build();
    }

    @Bean // 패스워드 인코더로 사용할 빈 등록
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint CustomAuthenticationEntryPoint(){
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setCharacterEncoding("UTF-8");
                response.sendError(401, "인증이 필요합니다.");
            }
        };
    }

}
