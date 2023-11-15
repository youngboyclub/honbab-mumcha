package yougboyclub.honbabstop.dto;

import lombok.Getter;
import yougboyclub.honbabstop.domain.Board;

import java.time.format.DateTimeFormatter;

@Getter
public class ResponseMyPageList {

  private final Long id; //Board id
  private final Long userId; // 모집글 작성자 Id
  private final String title; // 글 제목
  private final int status; // 모집글 상태
  private final String createAt; // 모집글 등록일
  private final String restaurantName; // 식당 이름
  private final String restaurantAddress; // 식당 주소

  public ResponseMyPageList(Board board) {
    this.id = board.getId();
    this.userId = board.getWriter().getId();
    this.title = board.getTitle();
    this.status = board.getStatus();
    this.createAt = board.getRegDate().format(DateTimeFormatter.ofPattern("yy/MM/dd"));
    this.restaurantName = board.getRestaurantName();
    this.restaurantAddress = board.getRestaurantAddress();
  }
}