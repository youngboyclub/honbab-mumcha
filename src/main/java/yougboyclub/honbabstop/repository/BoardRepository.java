package yougboyclub.honbabstop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;

import java.util.List;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
  //모집글 작성
//  Board createBoard(RequestBoardDto requestBoardDto);

  //게시글 번호로 게시물 가져오회
  Board findBoardByBoardNo(Long boardNo);

  //모든 모집글 조회
  List<Board> findAllBoard();

  //음식 카테고리로 게시글 조회
  List<Board> findByFoodCategory(String foodCategory);

  //장소 카테고리로 게시글 조회
  List<Board> findByPlaceCategory(String placeCategory);

  //내가 작성한 글 모든 게시글을 조회
  List<Board> findByWriter(User user);

  //모집글 수정
  Board update(long boardNo, UpdateBoardRequest request);
}
