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
@AttributeOverride(name = "id", column = @Column(name = "like_no"))
public class Likes extends BaseEntity{

    @ManyToOne(targetEntity = User.class)
    private Long userNo;

    @ManyToOne(targetEntity = User.class)
    private Long boardNo;

    @Column(name = "status", nullable = false, columnDefinition = "int default 1")
    private int status;

    @Builder
    public Likes(Long userNo, Long boardNo, int status) {
        this.userNo = userNo;
        this.boardNo = boardNo;
        this.status = status;
    }
}
