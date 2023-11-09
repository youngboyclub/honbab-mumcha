package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;

import java.util.List;

public interface BoardService {
    List<Board> findAllBoard();
    List<Board> findByFoodCategory(String foodCategory);
    List<Board> findByPlaceCategory(String placeCategory);

    Board createBoard(RequestBoardDto requestBoardDto);

    Board update(long id, UpdateBoardRequest request);
}
