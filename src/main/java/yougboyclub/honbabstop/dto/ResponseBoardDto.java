package yougboyclub.honbabstop.dto;


import lombok.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Participants;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class ResponseBoardDto {
  private Long boardId;
  private String restaurantName;
  private User writer;
  private String restaurantAddress;
  private String foodCategory;
  private LocalDate time;
  private int people;
  private String title;
  private String content;
  private Long hit;
  private int status; //모집글의 상태 0:모집 완료, 1:모집 중, -1:완료
  private LocalDateTime regDate;
  private LocalDateTime lastModified;
//  private List<ResponseParticipant> joinUser;  // 신청자 리스트

  //생성자
  public ResponseBoardDto(Board board) {
    this.boardId = board.getId();
    this.restaurantName = board.getRestaurantName();
    this.restaurantAddress = board.getRestaurantAddress();
    this.writer = board.getWriter();
    this.foodCategory = board.getFoodCategory();
    this.time = board.getTime();
    this.people = board.getPeople();
    this.title = board.getTitle();
    this.content = board.getContent();
    this.hit = board.getHit();
    this.status = 1;
    this.regDate = board.getRegDate();
    this.lastModified = board.getLastModified();
//    this.joinUser = Participants.getJoins().stream().map(
//        ResponseParticipant::new).collect(Collectors.toList());
  }

//  //게시글의 모집 상태(int)를 한글로 변환하는 메소드
//  public String changeStatusToKorean(int status) {
//    if (status == 1) {
//      return "모집중";
//    }
//    if (status == 0) {
//      return "모집 완료";
//    }
//    if (status == -1) {
//      return "모집 종료";
//    }
//    return time.toString();
//  }
}