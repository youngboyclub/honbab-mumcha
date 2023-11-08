package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yougboyclub.honbabstop.domain.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByFoodCategory(String foodCategory);
}
