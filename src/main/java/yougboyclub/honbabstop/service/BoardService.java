package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;

import java.util.List;

public interface BoardService {
    List<Board> findAllBoard();
    List<Board> findByFoodCategory(String foodCategory);
    List<Board> findByPlaceCategory(String placeCategory);
    Board createBoard(RequestBoardDto requestBoardDto, Long id);
    // 내가 작성한 글을 조회
    public List<Board> findByWriter(User user);
    Board update(Long id, UpdateBoardRequest request);

    ResponseBoardDto getBoardDetail(Long id, User user);
}