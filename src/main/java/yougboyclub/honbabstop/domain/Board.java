package yougboyclub.honbabstop.domain;


import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
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

  @Column(name = "TIME")
  private LocalTime time;

  @Column(name = "food_category")
  private String foodCategory;

  @Column(name = "place_category")
  private String placeCategory;

  @Column(name = "status")
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

  @Column(name = "LOCATION_X")
  private String locationX;

  @Column(name = "LOCATION_Y")
  private String locationY;


  // 조회수 증가
  public void increaseHit(User writer) {
    if (!this.writer.equals(writer)) {
      this.hit += 1;
    }
  }

//   //게시글 수정 / 제목, 내용, 인원, 식당 이름, 식당 주소 변경
//   public void update(RequestBoardDto request) {
//     this.title = request.getTitle();
//     this.content = request.getContent();
//     this.people = request.getPeople();
//     this.restaurantName = request.getRestaurantName();
//     this.restaurantAddress = request.getRestaurantAddress();
//   }
// }

    public void update(String title, String content, LocalTime time, String foodCategory, String placeCategory, int people, String restaurantName, String restaurantAddress){
        this.title = title;
        this.content = content;
        this.time = time;
        this.foodCategory = foodCategory;
        this.placeCategory = placeCategory;
        this.people = people;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
    }

}
