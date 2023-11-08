package yougboyclub.honbabstop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    //key와 data를 Redis에 저장한다. 만약 데이터에 만료 시간을 설정하고 싶다면 세 번째 파라미터로 Duration 객체를 전달한다.

    public void setValues(String key, String value, Duration expiration) {
        redisTemplate.opsForValue().set(key, value, expiration);
    }

    //key 파라미터로 받아 key를 기반으로 데이터를 조회한다.
    @Transactional(readOnly = true)
    public String getValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(key) == null) {
            return "false";
        }
        return (String) values.get(key);
    }

    //key를 파라미터로 받아 key를 기반으로 데이터를 삭제한다.
    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }
}
