package yougboyclub.honbabstop.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@ToString
@Entity
@AttributeOverride(name = "id", column = @Column(name = "user_no"))
//Serializable 로그인 시 세션에 회원정보를 담기 위한 설정
public class User extends BaseEntity implements Serializable {


    @Column(name = "user_email")
    private String email;

    @Column(name = "user_pwd")
    private String password;

    @Column(name = "user_name")
    private String name;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mbti")
    private String mbti;

    @Builder
    public User(Long id, LocalDateTime regDate, String email, String password, String name, LocalDate birth, String phone,
                String address, String gender, String mbti) {
        super(id, regDate);
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.mbti = mbti;
    }

}
