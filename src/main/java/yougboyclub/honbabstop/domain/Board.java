package yougboyclub.honbabstop.domain;


import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
@AttributeOverride(name = "id", column = @Column(name = "board_id"))
@ToString
public class Board extends BaseEntity {

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private User writer;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "time")
  private LocalTime time;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "meet_date")
  private Date meetDate;

  @Column(name = "food_category")
  private String foodCategory;

  @Column(name = "place_category")
  private String placeCategory;

  @Column(name = "status")
  private int status;

  @Column(name = "people")
  private int people;

  @Column(name = "restaurant_name")
  private String restaurantName;

  @Column(name = "restaurant_address")
  private String restaurantAddress;

  @Column(name = "hit")
  private Long hit;

  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  @UpdateTimestamp
  @Column(name = "last_modified")
  private LocalDateTime lastModified;

  @Column(name = "location_x")
  private String locationX;

  @Column(name = "location_y")
  private String locationY;


  // 조회수 증가 메소드
  public void increaseHit() {
      this.hit += 1L;
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