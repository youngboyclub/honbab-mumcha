package yougboyclub.honbabstop.dto;


import lombok.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class RequestBoardDto {
  private String restaurantName;
  private String restaurantAddress;
  private User writer;
  private String foodCategory;
  private LocalTime time;
  private int people;
  private String title;
  private String content;
  private Long hit;
  private int status;

  public Board toEntity() {
    return Board.builder()
        .restaurantName(restaurantName)
        .restaurantAddress(restaurantAddress)
        .writer(writer)
        .time(time)
        .people(people)
        .title(title)
        .content(content)
        .hit(hit)
        .status(status)
        .build();
  }
}