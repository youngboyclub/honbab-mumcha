package yougboyclub.honbabstop.dto;

import lombok.*;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.domain.Participants;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class LikesDto {
    private Participants participantsNo;
    private String email;
    private Long boardNo;
    private int status;

    // DTO를 Likes 엔티티로 변환하는 메서드
    @Builder
    public Likes toEntity() {
        return Likes.builder()
                .user(null)
                .board(null)
                .build();
    }
}
