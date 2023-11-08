package yougboyclub.honbabstop.domain;

import lombok.*;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;

@Table(name = "PARTICIPANTS")
@Entity
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "participants_no"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participants extends BaseEntity {

    @ManyToOne
    @JoinColumn(name ="user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name="board_no")
    private Board board;

    @Column(name = "status")
    private int status;


}
