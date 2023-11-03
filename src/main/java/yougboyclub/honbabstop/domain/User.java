package yougboyclub.honbabstop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "USERS")
@Entity
public class User extends BaseEntity{


    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_PWD")
    private String password;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "BIRTHDAY")
    private LocalDateTime birth;

    @Column(name = "PHONE_NUMBER")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "GENDER")
    private char gender;

    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

}
