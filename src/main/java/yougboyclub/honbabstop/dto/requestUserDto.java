package yougboyclub.honbabstop.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class requestUserDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private LocalDateTime birth;

    private String phone;

    private String address;

    private char gender;

    private LocalDateTime regDate;

    //빌더 패턴을 사용해 DTO를 엔티티로 만들어주는 메서드
    //생성자를 사용해서 객체를 생성.
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .birth(birth)
                .phone(phone)
                .address(address)
                .gender(gender)
                .regDate(regDate)
                .build();
    }

}
