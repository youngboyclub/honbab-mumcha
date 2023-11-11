package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;
import yougboyclub.honbabstop.service.BoardService;
import yougboyclub.honbabstop.service.LikesService;
import yougboyclub.honbabstop.service.LikesServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class BoardController {

  @Autowired
  private final BoardService boardService;
  @Autowired
  private final LikesService likesService;

  //모든 모집글 조회
  @GetMapping
  public List<ResponseBoardDto> showBoardList() {
    List<Board> boards = boardService.findAllBoard();
    List<ResponseBoardDto> boardDtos = boards.stream()
        .map(board -> new ResponseBoardDto(board))
        .collect(Collectors.toList());

    System.out.println("여기까지 오느라 수고했어1::" + boards);
    System.out.println("여기까지 오느라 수고했어2::" + boardDtos);
    return boardDtos;
  }

  //신규 모집글 작성
  @PostMapping("/new")
  public ResponseEntity<String> createBoard(@RequestBody RequestBoardDto dto) {
    System.out.println("dto = " + dto);
    boardService.createBoard(dto);
    return ResponseEntity.ok().body("모집글 등록에 성공하였습니다");
  }

  //모집글 조회(음식)
  @GetMapping("/food/{foodCategory}")
  public List<ResponseBoardDto> showBoardListByFood(@PathVariable String foodCategory) {
    System.out.println("음식카테고리" + foodCategory);
    List<Board> boards = boardService.findByFoodCategory(foodCategory);
    List<ResponseBoardDto> boardDtos = boards.stream()
        .map(board -> new ResponseBoardDto(board))
        .collect(Collectors.toList());
    System.out.println("여기까지 오느라 수고했어3::" + boards);
    System.out.println("여기까지 오느라 수고했어4::" + boardDtos);
    return boardDtos;
  }

  //모집글 조회(장소)
  @GetMapping("/place/{placeCategory}")
  public List<ResponseBoardDto> showBoardListByPlace(@PathVariable String placeCategory) {
    System.out.println("장소카테고리" + placeCategory);
    List<Board> boards = boardService.findByPlaceCategory(placeCategory);
    List<ResponseBoardDto> boardDtos = boards.stream()
        .map(board -> new ResponseBoardDto(board))
        .collect(Collectors.toList());
    System.out.println("여기까지 오느라 수고했어5::" + boards);
    System.out.println("여기까지 오느라 수고했어6::" + boardDtos);
    return boardDtos;
  }

  //모집글 상세 조회(개별 상세조회)
  @GetMapping("/{id}")
  public ResponseEntity<ResponseBoardDto> findById(@PathVariable Long id) {
    return new ResponseEntity<>(boardService.findById(id), HttpStatus.OK);
  }

  //모집글 수정
  @PutMapping("/edit/{id}")
  public ResponseEntity<Board> updateById(@PathVariable Long id, @RequestBody UpdateBoardRequest request) {
    Board updateBoard = boardService.updateById(id, request);
    return ResponseEntity.ok().body(updateBoard);
  }

  //모집글 삭제
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ResponseBoardDto> deleteById(@PathVariable Long id) {
    boardService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}