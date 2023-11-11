package yougboyclub.honbabstop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateBoardRequest {
  private Long boardId;
  private User writer;
  private String title;
  private String content;
  private LocalTime time;
  private String foodCategory;
  private String placeCategory;
  private int status;
  private int people;
  private String restaurantName;
  private String restaurantAddress;
  private LocalDateTime regDate;
  private Long hit;
  private LocalDateTime lastModified;
  private String locationX;
  private String locationY;
}