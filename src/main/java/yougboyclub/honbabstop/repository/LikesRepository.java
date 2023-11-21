package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.domain.User;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByUser(User user);

    Likes findByBoardAndAndUser(Board board, User user);

    //내가 좋아요 누른 게시글 가져오기
    @Query("select l.board from Likes l where l.user =:user")
    List<Board> findBoardByUserLike(@Param("user")User user);
}
