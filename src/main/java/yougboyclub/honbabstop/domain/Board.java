package yougboyclub.honbabstop.domain;


import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "partyboard")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "board_no"))
@ToString
public class Board extends BaseEntity {

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_no")
  private User writer;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  //모임 날짜와 시간이 담기는 필드
  @Column(name = "time")
  private LocalTime time;

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

  @OneToMany(mappedBy = "partyboard")
  private List<Participants> applications;  //모집글에 작성된 참가신청


  // 조회수 증가
  public void increaseHit(User writer) {
    if (!this.writer.equals(writer)) {
      this.hit += 1;
    }
  }

  //게시글 수정 / 제목, 내용, 인원, 식당 이름, 식당 주소 변경
  public void update(RequestBoardDto request) {
    this.title = request.getTitle();
    this.content = request.getContent();
    this.people = request.getPeople();
    this.restaurantName = request.getRestaurantName();
    this.restaurantAddress = request.getRestaurantAddress();
  }
}