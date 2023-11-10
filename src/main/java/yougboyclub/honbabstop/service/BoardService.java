package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;

import java.util.List;

public interface BoardService {
  //모집글 작성
  Board createBoard(RequestBoardDto requestBoardDto);

  //모집글 전체 조회
  List<Board> findAllBoard();

  //모집글 상세 조회(개별 상세조회)
  Board getBoardDetail(Long boardNo, User user);

  //음식 카테고리로 게시글 조회
  List<Board> findByFoodCategory(String foodCategory);

  //장소 카테고리로 게시글 조회
  List<Board> findByPlaceCategory(String placeCategory);

  //내가 작성한 모든 게시글을 조회
  List<Board> findByWriter(User user);

  //모집글 수정
  Board update(Long boardNo, UpdateBoardRequest request);
}
