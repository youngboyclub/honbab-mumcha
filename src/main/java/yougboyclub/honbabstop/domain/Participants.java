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

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_no")
  private User user;

  @ManyToOne(targetEntity = Board.class)
  @JoinColumn(name = "board_no")
  private Board board;

  @Column(name = "status")
  private int status;

  public Participants(Long id, LocalDateTime regDate, User user, Board board) {
    super(id, regDate);
    this.user = user;
    this.board = board;
  }

  public void getJoins(){

  }
}