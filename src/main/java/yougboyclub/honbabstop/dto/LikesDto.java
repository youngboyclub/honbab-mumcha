package yougboyclub.honbabstop.dto;

import lombok.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.domain.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class LikesDto {

  private User userId;
  private Board boardId;
  private int status;

  // DTO를 Likes 엔티티로 변환하는 메서드
  public Likes toEntity() {
    return Likes.builder()
        .userId(userId.getId())
        .boardId(boardId.getId())
        .status(status)
        .build();
  }
}
