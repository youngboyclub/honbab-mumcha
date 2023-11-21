package yougboyclub.honbabstop.service;

import org.springframework.data.repository.query.Param;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.domain.User;

import java.util.List;

public interface LikesService {
    List<Likes> findByUser(User user);

    Likes findByBoardAndUser(Board board, User user);

    void createLikes(Likes likes);

    void deleteLikes(Likes likes);
    // 내가 좋아요 설정한 글 가져오기
    List<Board> findBoardByUserLike(User user);

}
