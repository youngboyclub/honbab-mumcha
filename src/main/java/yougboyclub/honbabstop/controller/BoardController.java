package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;
import yougboyclub.honbabstop.service.BoardService;
import yougboyclub.honbabstop.service.LikesService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {

    @Autowired
    private final BoardService boardService;
    @Autowired
    private final LikesService likesService;

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


    @PostMapping("/new")
    public ResponseEntity<String> createBoard(@RequestBody RequestBoardDto dto) {
        System.out.println("dto = " + dto);
        boardService.createBoard(dto);
        return ResponseEntity.ok().body("게시글 등록에 성공하였습니다");
    }

    @GetMapping("/food/{foodCategory}")
    public List<ResponseBoardDto> showBoardListByFood(@PathVariable String foodCategory) {
        System.out.println("음식카테고리::" + foodCategory);
        List<Board> boards = boardService.findByFoodCategory(foodCategory);
        List<ResponseBoardDto> boardDtos = boards.stream()
                .map(board -> new ResponseBoardDto(board))
                .collect(Collectors.toList());
        System.out.println("여기까지 오느라 수고했어3::" + boards);
        System.out.println("여기까지 오느라 수고했어4::" + boardDtos);
        return boardDtos;
    }

    @GetMapping("/place/{placeCategory}")
    public List<ResponseBoardDto> showBoardListByPlace(@PathVariable String placeCategory) {
        System.out.println("장소카테고리::" + placeCategory);
        List<Board> boards = boardService.findByPlaceCategory(placeCategory);
        List<ResponseBoardDto> boardDtos = boards.stream()
                .map(board -> new ResponseBoardDto(board))
                .collect(Collectors.toList());
        System.out.println("여기까지 오느라 수고했어5::" + boards);
        System.out.println("여기까지 오느라 수고했어6::" + boardDtos);
        return boardDtos;
    }

    @GetMapping("/findby/{keyword}")
    public List<ResponseBoardDto> showBoardListByKeyword(@PathVariable String keyword) {
        System.out.println("키워드::" + keyword);
        List<Board> boards = boardService.findByKeyword(keyword);
        List<ResponseBoardDto> boardDtos = boards.stream()
                .map(board -> new ResponseBoardDto(board))
                .collect(Collectors.toList());
        System.out.println("여기까지 오느라 수고했어7::" + boards);
        System.out.println("여기까지 오느라 수고했어8::" + boardDtos);
        return boardDtos;
    }


    @GetMapping("/api/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody UpdateBoardRequest request) {
        Board updateBoard = boardService.update(id, request);

        return ResponseEntity.ok().body(updateBoard);
    }

    //모집글 상세 조회(개별 상세조회)
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBoardDto> getBoardDetail(@PathVariable Long id, User user) {
        return ResponseEntity.ok(boardService.getBoardDetail(id, user)
        );
    }
}