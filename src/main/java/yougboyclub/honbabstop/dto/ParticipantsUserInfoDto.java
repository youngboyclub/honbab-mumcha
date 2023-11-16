package yougboyclub.honbabstop.dto;

import lombok.*;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipantsUserInfoDto {

    private String name;
    private LocalDate birth;
    private String gender;

    @Builder
    public ParticipantsUserInfoDto(User user) {
        this.name = user.getName();
        this.birth = user.getBirth();
        this.gender = user.getGender();
    }
}
