package yougboyclub.honbabstop.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import yougboyclub.honbabstop.dto.RequestBoardDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "partyboard")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "BOARD_NO"))
@ToString
public class Board extends BaseEntity {

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "USER_NO")
  private User writer;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "CONTENT")
  private String content;

  @Column(name = "TIME")
  private LocalTime time;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "MEET_DATE")
  private Date meetDate;

  @Column(name = "FOOD_CATEGORY")
  private String foodCategory;

  @Column(name = "PLACE_CATEGORY")
  private String placeCategory;

  @Column(name = "STATUS")
  private int status;

  @Column(name = "PEOPLE")
  private int people;

  @Column(name = "RESTAURANT_NAME")
  private String restaurantName;

  @Column(name = "RESTAURANT_ADDRESS")
  private String restaurantAddress;

  @Column(name = "HIT")
  private Long hit;

  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  @UpdateTimestamp
  @Column(name = "LAST_MODIFIED")
  private LocalDateTime lastModified;

  @Column(name = "location_x")
  private String locationX;

  @Column(name = "location_y")
  private String locationY;


  // 조회수 증가
  public void increaseHit(User writer) {
    if (!this.writer.equals(writer)) {
      this.hit += 1;
    }
  }

  public void update(String title, String content, LocalTime time, Date meetDate, String foodCategory, String placeCategory, int people, String restaurantName, String restaurantAddress, String locationX, String locationY) {
    this.title = title;
    this.content = content;
    this.time = time;
    this.meetDate = meetDate;
    this.foodCategory = foodCategory;
    this.placeCategory = placeCategory;
    this.people = people;
    this.restaurantName = restaurantName;
    this.restaurantAddress = restaurantAddress;
    this.locationX = locationX;
    this.locationY = locationY;
  }
}