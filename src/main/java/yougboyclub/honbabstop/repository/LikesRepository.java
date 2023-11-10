package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yougboyclub.honbabstop.domain.Likes;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    List<Likes> findByUserNo(Long id);
}
