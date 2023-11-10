package yougboyclub.honbabstop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;
import yougboyclub.honbabstop.repository.BoardRepository;
import yougboyclub.honbabstop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  @Autowired
  private final BoardRepository boardRepository;

  @Autowired
  private final UserRepository userRepository;

  //모집글 작성
  @Override
  public Board createBoard(RequestBoardDto requestBoardDto) {
    Optional<User> byId = userRepository.findById(1L);

    if (byId.isEmpty()) {
      throw new IllegalArgumentException("Invalid userId: " + 1L);
    }

    User user = byId.get();

    Board board = requestBoardDto.toEntity();
    board.setWriter(user);
    return boardRepository.save(board);
  }

  //모든 모집글 조회
  @Override
  public List<Board> findAllBoard() {
    return boardRepository.findAll();
  }

  @Override
  public String toString() {
    return super.toString();
  }

  //모집글의 상세 정보 조회
  public Board getBoardDetail(Long boardNo, User user) {
    //boarNo의 게시물 가져오기
    Board getBoard = boardRepository.findBoardByBoardNo(boardNo);

    //조회자!=작성자인 경우만 조회한 게시물의 조회수 올라감
    getBoard.increaseHit(user);
    return getBoard;
  }

  //음식 카테고리로 모집글 조회
  @Override
  public List<Board> findByFoodCategory(String foodCategory) {
    return boardRepository.findByFoodCategory(foodCategory);
  }

  //장소 카테고리로 모집글 조회
  @Override
  public List<Board> findByPlaceCategory(String placeCategory) {
    return boardRepository.findByPlaceCategory(placeCategory);
  }

  //내가 작성한 모든 모집글 조회
  @Override
  public List<Board> findByWriter(User user) {
    return boardRepository.findAllBoard();
  }

  //모집글 수정
  @Override
  public Board update(Long boardNo, UpdateBoardRequest request) {
    Board board = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("not found : " + boardNo));
    board.update(request);
    return board;
  }

  //모집글 삭제
  public void deleteById(ResponseBoardDto res) {
    Board getBoard = boardRepository.findBoardByBoardNo(res.getBoardId());
    boardRepository.deleteById(getBoard.getId());
  }


}


