package yougboyclub.honbabstop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "participants")
@Entity
@Data
@Setter
@AttributeOverride(name = "id", column = @Column(name = "participants_no"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participants extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_no")
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