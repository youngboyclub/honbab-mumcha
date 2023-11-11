package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;

import java.util.List;

public interface BoardService {
  Board createBoard(RequestBoardDto requestBoardDto);

  List<Board> findAllBoard();

  List<Board> findByFoodCategory(String foodCategory);

  List<Board> findByPlaceCategory(String placeCategory);

  public List<Board> findByWriter(User user);

  ResponseBoardDto findById(Long boardNo);

  Board updateById(Long id, UpdateBoardRequest request);

  void deleteById(Long boardNo);

}