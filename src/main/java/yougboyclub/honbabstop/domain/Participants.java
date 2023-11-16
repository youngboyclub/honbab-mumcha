package yougboyclub.honbabstop.domain;

import lombok.*;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Table(name = "participants")
@Entity
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "participants_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participants extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "board_id")
  private Board board;

  @Column(name = "status")
  private int status;
}