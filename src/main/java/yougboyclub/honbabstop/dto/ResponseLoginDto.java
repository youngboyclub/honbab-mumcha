package yougboyclub.honbabstop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yougboyclub.honbabstop.domain.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseLoginDto {
    private String token;
    private int expireTime;
    private User user;
}
