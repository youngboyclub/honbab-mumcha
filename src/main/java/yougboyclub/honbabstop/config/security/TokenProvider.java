package yougboyclub.honbabstop.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * JWT : 전자 서명이 된 토큰
 * JSON 형태로 구성된 토큰
 * header.payload.signature
 *
 * header : typ (해당 토큰의 타입), alg (토큰을 서명하기 위해 사용된 해시 알고리즘)
 * payload: sub (해당 토큰의 주인), iat (토큰이 발행된 시간), exp (토큰이 만료되는 시간)
 */
@Service
public class TokenProvider {
    //JWT 생성 및 검증을 위한 키
    private static final String SECURITY_KEY = "jwtseckey!@";

    //JWT 생성하는 메서드
    public String create (String email){
        Date expireTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); //현재시간에 +1시간을 하여 유효시간 설정

        //빌더 패턴으로 JWT토큰 생성
        return Jwts.builder()
                .setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))//토큰 생성일
                .setExpiration(expireTime) // 유효 시간 설정
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY) // HS256 알고리즘과 secretKey를 통해 토큰 암호화
                .compact();
    }

    //토큰으로부터 회원이메일(email)을 받아 옴.
    public static String validate (String token){
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}
