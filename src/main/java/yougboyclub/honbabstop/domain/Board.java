package yougboyclub.honbabstop.domain;


import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "partyboard")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "board_no"))
@ToString
public class Board extends BaseEntity {

  //연관 관계 매핑
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_no")
  private User writer;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  //모임 날짜와 시간이 담기는 필드
  @Column(name = "time")
  private LocalDate time;

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

  @OneToMany(targetEntity = Participants.class)
  private List<Participants> join; //이 모집글에 작성된 참가신청

  // 작성자 본인을 제외한 사용자가 게시물 들어가면 조회수 증가
  public void increaseHit(User writer) {
    if (!this.writer.equals(writer)) {
      this.hit += 1;
    }
  }

  //게시글 수정/ 제목, 내용, 카테고리, 식당 이름, 주소만 변경 가능
  public void update(UpdateBoardRequest request) {
    this.writer = request.getWriter();
    this.title = request.getTitle();
    this.content = request.getContent();
    this.time = LocalDate.from(request.getTime());
    this.foodCategory = request.getFoodCategory();
    this.placeCategory = request.getPlaceCategory();
    this.status = request.getStatus();
    this.people = request.getPeople();
    this.restaurantName = request.getRestaurantName();
    this.restaurantAddress = request.getRestaurantAddress();
    this.lastModified = LocalDateTime.now();
  }

  public Long getWriterId() {
    return this.writer.getId();
  }

  public void setWriter(User writer) {
    this.writer = writer;
  }

  public boolean haveNoJoins() {
    return this.join.isEmpty();
  }
}