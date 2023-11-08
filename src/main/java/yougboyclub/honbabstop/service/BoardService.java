package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;

import java.util.List;

public interface BoardService {
    List<Board> findAllBoard();

    Board createBoard(RequestBoardDto requestBoardDto);


    // 내가 작성한 글을 조회하기 위한 메서드
    public List<Board> findByWriter(User user);
}
