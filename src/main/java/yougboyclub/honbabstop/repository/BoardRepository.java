package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 마이페이지에서 내가 쓴 글 조회
    // 유저 정보를 가져와서 조회한다.
    List<Board> findByWriter(User user);

    List<Board> findByFoodCategory(String foodCategory);

    List<Board> findByPlaceCategory(String placeCategory);

    @Query("SELECT b FROM Board b WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR b.foodCategory LIKE %:keyword%) OR " +
            "(:keyword IS NULL OR :keyword = '' OR b.placeCategory LIKE %:keyword%) OR " +
            "(:keyword IS NULL OR :keyword = '' OR b.title LIKE %:keyword%) OR " +
            "(:keyword IS NULL OR :keyword = '' OR b.content LIKE %:keyword%)")
    List<Board> findByKeyword(@Param("keyword") String keyword);
}

