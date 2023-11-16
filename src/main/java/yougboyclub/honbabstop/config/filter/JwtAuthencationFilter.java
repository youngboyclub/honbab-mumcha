package yougboyclub.honbabstop.config.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import yougboyclub.honbabstop.config.security.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthencationFilter extends OncePerRequestFilter {

    //requset 가 들어왔을 때 requset header의 Authorization 필드의 Bearer Token을 가져옴
    //가져온 토큰을 검증하고 검증 결과를 securityContext에 추가

    private final TokenProvider tokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{

        String token = parseBearerToken(request);

        if(token != null && !token.equalsIgnoreCase("null")) {
            //토큰 검증해서 payload의 email을 가져옴.
            String userEmail = tokenProvider.validate(token);

            //SecurityContext에 추가될 객체 => 추가되어야 해당 쓰레드가 지속적으로 내용을 알 수 있음.
            AbstractAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //SecurityContext에 AbstractAuthenticationToken 객체를 추가해서 해당 쓰레드가 지속적으로 인증정보를 가질 수 있도록 함.
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);

        }

        }catch (Exception e){
            e.printStackTrace();
        }

        filterChain.doFilter(request,response);

    }

    //request header에서 Authorization 필드의 Bearer Token을 가져오는 메서드
    private String parseBearerToken (HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer ")){// 빈 값, null, 공백이 있을 경우 false 반환.
            return bearerToken.substring(7); //Bearer를 제외한 토큰 값
        }
        return null;
    }
}
