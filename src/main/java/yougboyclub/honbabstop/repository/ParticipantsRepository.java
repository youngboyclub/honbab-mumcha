package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Participants;
import yougboyclub.honbabstop.domain.User;

import java.util.List;

public interface ParticipantsRepository extends JpaRepository<Participants, Long> {

    //내가 참여한 게시들 가져오기
//    @Query("select p.board from participants p where p.user = :user and p.board.writer != :user")

//    @Query("select p.board from Participants p where p.user = :user")
//    List<Board> findByUser(User user);

    // 참여한 글 중에서 내가 쓴 글이 아닌 것만 가져오기
    @Query("SELECT p.board FROM Participants p WHERE p.user = :user AND p.board.writer != :user")
    //@Query("select p.board from Participants p where p.user = :user") // and p.board.writer != :user"
    List<Board> findByUserNonWriter(@Param("user") User user);

    @Query("select p.board from Participants p where p.user =:user")

//     List<Board> findByUser(@Param("user")User user);
// todo: 메서드명 모두 고치기
    //참가한 글에 해당하는 유저 가져오는 쿼리
    @Query("select u from User u where u in(select  p.user from Participants p where p.board =:board)")
    List<User> findByBoardPartyUser(@Param("board")Board board);

    List<Board> findBoardByUser(@Param("user") User user);

    Participants findByBoardAndAndUser(Board board, User user);

    List<Participants> findByUser(User user);

}
