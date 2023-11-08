package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.dto.RequestBoardDto;

import java.util.List;

public interface BoardService {
    List<Board> findAllBoard();
    List<Board> findByFoodCategory(String foodCategory);
    List<Board> findByPlaceCategory(String placeCategory);

    Board createBoard(RequestBoardDto requestBoardDto);
}
