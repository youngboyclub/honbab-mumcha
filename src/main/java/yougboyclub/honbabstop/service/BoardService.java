package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

public interface BoardService {

  //모집글
  Board createBoard(RequestBoardDto requestBoardDto);

  List<Board> findAllBoard();

  List<Board> findByFoodCategory(String foodCategory);

  List<Board> findByPlaceCategory(String placeCategory);

  // 내가 작성한 글을 조회
  public List<Board> findByWriter(User user);

  Board update(Long id, UpdateBoardRequest request);

  List<Board> findByKeyword(String keyword);

  List<Board> findByUserNonWriter(User user);

  List<Board> findByUser(User user);

  void deleteById(Long id, Long userId);

  //모집글 상세조회(모집글 번호)
  Board findByIdAndUser(Long id, User user);

  Board findById(Long id);

  Board updateById(Long id, UpdateBoardRequest request);


}