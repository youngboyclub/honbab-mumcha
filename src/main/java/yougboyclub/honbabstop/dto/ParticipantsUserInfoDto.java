package yougboyclub.honbabstop.dto;

import lombok.*;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipantsUserInfoDto {

    private String name;
    private LocalDate birth;
    private String gender;
    private String email;

    @Builder
    public ParticipantsUserInfoDto(User user) {
        this.name = user.getName();
        this.birth = user.getBirth();
        this.gender = user.getGender();
        this.email = user.getEmail();
    }
}
