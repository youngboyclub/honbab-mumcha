package yougboyclub.honbabstop.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class RequestBoardDto {
  private String title;
  private User writer;
  private String content;
  private LocalTime time;
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd")
  private Date meetDate;
  private String foodCategory;
  private String placeCategory;
  private int people;
  private String restaurantName;
  private String restaurantAddress;
  private String locationX;
  private String locationY;

  @Builder
  public Board toEntity() {
    return Board.builder()
        .title(title)
        .writer(writer)
        .content(content)
        .time(time)
        .meetDate(meetDate)
        .foodCategory(foodCategory)
        .placeCategory(placeCategory)
        .people(people)
        .restaurantName(restaurantName)
        .restaurantAddress(restaurantAddress)
        .locationX(locationX)
        .locationY(locationY)
        .build();
  }
}