package yougboyclub.honbabstop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateBoardRequest {
  private User writer;
  private String title;
  private String content;
  private LocalTime time;
  private Date meetDate;
  private String foodCategory;
  private String placeCategory;
  private int people;
  private String restaurantName;
  private String restaurantAddress;
  private LocalDateTime lastModified;
  private String locationX;
  private String locationY;
}