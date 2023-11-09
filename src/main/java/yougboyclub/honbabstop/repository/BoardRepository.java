package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;

import java.util.List;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    // 마이페이지에서 내가 쓴 글 조회
    // 유저 정보를 가져와서 조회한다.
    List<Board> findByWriter(User user);

    List<Board> findByFoodCategory(String foodCategory);
    List<Board> findByPlaceCategory(String placeCategory);
}
