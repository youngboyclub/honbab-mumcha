package yougboyclub.honbabstop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "like_no"))
public class Likes extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;


    @Builder
    public Likes(Long id, LocalDateTime regDate, User user, Board board) {
        super(id, regDate);
        this.user = user;
        this.board = board;
    }
}
