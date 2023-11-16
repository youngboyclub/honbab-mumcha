package yougboyclub.honbabstop.dto;

import lombok.*;
import yougboyclub.honbabstop.domain.Participants;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ParticipantsDto {
    private Participants participantsNo;
    private String email;
    private String username;
    private Long boardNo;
    private int status;

    @Builder
    public Participants toEntity() {
        return Participants.builder()
                .user(null)
                .board(null)
                .status(status)
                .build();
    }
}