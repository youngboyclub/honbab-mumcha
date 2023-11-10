package yougboyclub.honbabstop.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import yougboyclub.honbabstop.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestUserDto {

    @NotBlank //공백 없음
    @Email//email 형식 맞추기
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    private String userName;

    @NotNull
    //json의 날짜 형식을 변환.
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd")
    private LocalDate birth;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
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
