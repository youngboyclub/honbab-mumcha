package yougboyclub.honbabstop.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import yougboyclub.honbabstop.domain.User;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class requestUserDto {

    private String email;

    private String password;

    private String userName;

    //json의 날짜 형식을 변환.
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd")
    private LocalDate birth;

    private String phone;

    private String address;

    private String gender;

    private String mbti;


    //빌더 패턴을 사용해 DTO를 엔티티로 만들어주는 메서드
    //생성자를 사용해서 객체를 생성.
    public User toEntity(){
        return User.builder()
                .name(userName)
                .email(email)
                .password(password)
                .birth(birth)
                .phone(phone)
                .address(address)
                .gender(gender)
                .mbti(mbti)
                .build();
    }

}
