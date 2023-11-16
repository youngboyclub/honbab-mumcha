package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.domain.User;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByUser(User user);

    Likes findByBoardAndAndUser(Board board, User user);
}
