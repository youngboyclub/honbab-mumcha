package yougboyclub.honbabstop.domain;

import lombok.*;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "participants")
@Entity
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "participants_no"))
@NoArgsConstructor
public class Participants extends BaseEntity {
  //연관 관계 매핑
  @ManyToOne
  @JoinColumn(name = "user_no")
  private User user;

  @ManyToOne()
  @JoinColumn(name = "board_no")
  private Board board;

  @Column(name="status")  //참여 상태 0:디폴트 1:수락 -1:거부
  private int status;


  public Participants(Long id, LocalDateTime regDate, User user, Board board, int status) {
    super(id, regDate);
    this.user = user;
    this.board = board;
    this.status = status;
  }

  //사용자가 참여 신청시 디폴트 status=0
  public void applyJoin() {
    this.status = 0;
  }

  //게시글 작성자가 수락하면 status=1
  public void acceptJoin() {
    this.status = 1;
  }

  //게시글 작성자가 거부하면 status=-1
  public void rejectJoin() {
    this.status = -1;
  }

}