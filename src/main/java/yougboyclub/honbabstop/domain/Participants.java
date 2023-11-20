package yougboyclub.honbabstop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "participants")
@Entity
@Setter
@Getter
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

    @Builder
    public Participants(Long id, LocalDateTime regDate, User user, Board board, int status) {
        super(id, regDate);
        this.user = user;
        this.board = board;
        this.status = status;
    }
}