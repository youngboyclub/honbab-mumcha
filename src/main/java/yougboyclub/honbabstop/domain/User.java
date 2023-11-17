package yougboyclub.honbabstop.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@ToString
@Entity
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
//Serializable 로그인 시 세션에 회원정보를 담기 위한 설정
public class User extends BaseEntity implements Serializable, UserDetails {


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

    @Override //권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override //사용자의 id를 반환(고유한 값)
    public String getUsername() {
        return email; // 회원 ID를 반환.
    }

    @Override //사용자의 패스워드를 반환
    public String getPassword() {
        return password; // 회원 패스워드를 반환.
    }

    @Override //계정 만료여부 반환
    public boolean isAccountNonExpired() {
        return true; //true -> 만료되지 않음.
    }

    @Override //계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        //계정 잠금되었는지 확인하는 로직
        return true;
    }

    @Override //패스워드의 만료여부 반환
    public boolean isCredentialsNonExpired() {
        //패스워드가 만료되었는지 확인하는 로직
        return true;
    }

    @Override //계정 사용여부 반환
    public boolean isEnabled() {
        //게정이 사용 가능한지 확인하는 로직
        return true;
    }

    public void update(String email, String name, String address) {
        this.email = email;
        this.name = name;
        this.address = address;

    }
}
