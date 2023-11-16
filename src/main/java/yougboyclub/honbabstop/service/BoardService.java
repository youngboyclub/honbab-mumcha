package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;

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

  void deleteById(Long boardId);

  Board findById(Long id, User currentUser);

  Board updateById(Long id, UpdateBoardRequest request);
}


//   Board createBoard(RequestBoardDto requestBoardDto, Long id);
//
//   // 내가 작성한 글을 조회
//   public List<Board> findByWriter(User user);
//
//   Board update(Long id, UpdateBoardRequest request);
//
//   ResponseBoardDto getBoardDetail(Long id, User user);
//
//   List<Board> findAllBoard();
//
//   List<Board> findByFoodCategory(String foodCategory);
//
//   List<Board> findByPlaceCategory(String placeCategory);
//
//   List<Board> findByWriter(User user);
//
//   Board updateById(Long id, UpdateBoardRequest request);
//
//   List<Board> findAllBoard();
//
//   List<Board> findByFoodCategory(String foodCategory);
//
//   List<Board> findByPlaceCategory(String placeCategory);
