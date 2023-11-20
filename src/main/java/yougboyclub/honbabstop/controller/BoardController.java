package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;
import yougboyclub.honbabstop.service.BoardService;
import yougboyclub.honbabstop.service.LikesService;
import yougboyclub.honbabstop.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
@CrossOrigin
public class BoardController {

    private final BoardService boardService;
    private final LikesService likesService;
    private final UserService userService;

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
    public ResponseEntity<ResponseBoardDto> createBoard(@RequestBody RequestBoardDto dto) {
        System.out.println("이건 컨트롤러로 들어오는 dto: " + dto);
        Board board = boardService.createBoard(dto);
        ResponseBoardDto responseBoardDto = new ResponseBoardDto(board);
        return ResponseEntity.ok(responseBoardDto);
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
    @GetMapping("/boardDetails/{id}")
    public ResponseBoardDto findBoardDetailById(@PathVariable Long id, User currentUser) {
        User user = userService.findById(currentUser.getId()); // 현재 사용자 정보를 가져옴
        Board board = boardService.findByIdAndUser(id, user); // 조회수 증가 로직이 포함된 메소드 호출
        System.out.println("서비스 갔다온 보드: " + board);
        return new ResponseBoardDto(board);
    }


    //모집글 수정
    @PutMapping("/boardDetails/edit/{id}")
    public ResponseEntity<Board> updateById(@PathVariable Long id, @RequestBody UpdateBoardRequest request) {
        Board updateBoard = boardService.updateById(id, request);
        return ResponseEntity.ok().body(updateBoard);
    }

    //모집글 삭제
    @DeleteMapping("/boardDetails/delete/{id}")
    public ResponseEntity<ResponseBoardDto> deleteById(@PathVariable Long id) {
        boardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
}
