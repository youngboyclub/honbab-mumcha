package yougboyclub.honbabstop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private String username;
    private String email;

    // Getter, Setter 생략

    public UserInfo(String username, String email) {
        this.username = username;
        this.email = email;
    }
}