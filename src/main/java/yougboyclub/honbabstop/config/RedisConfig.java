package yougboyclub.honbabstop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@RequiredArgsConstructor
@Configuration
@EnableRedisRepositories//Redis를 사용한다고 명시해 주는 애너테이션
public class RedisConfig {
    private final RedisProperties redisProperties; // Redis 서버와의 연결 정보를 저장하는 객체

    // RedisProperties로 application.properties에 저장한 host, post를 연결
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        //LettuceConnectionFactory 객체를 생성하여 반환하는 메서드.
        // 이 객체는 Redis Java 클라이언트 라이브러리인 Lettuce를 사용해서 Redis 서버와 연결해 준다.
        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
    }

    //serializer 설정으로 redis-cli를 통해 직접 데이터를 조회할 수 있도록 설정
    //RedisTemplate: Redis 데이터를 저장하고 조회하는 기능
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //Redis 데이터를 직렬화하는 방식을 설정
        //Redis CLI를 사용해 Redis 데이터를 직접 조회할 때, Redis 데이터를 문자열로 반환해야하기 때문
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }
}
