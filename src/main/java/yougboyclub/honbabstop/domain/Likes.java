package yougboyclub.honbabstop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "like_id"))
public class Likes extends BaseEntity{

    @ManyToOne(targetEntity = User.class)
    private Long userId;

    @ManyToOne(targetEntity = Board.class)
    private Long boardId;

    @Column(name = "status", nullable = false, columnDefinition = "int default 1")
    private int status;

    @Builder
    public Likes(Long userId, Long boardId, int status) {
        this.userId = userId;
        this.boardId = boardId;
        this.status = status;
    }
}
