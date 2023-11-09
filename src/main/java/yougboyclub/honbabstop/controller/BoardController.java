package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;
import yougboyclub.honbabstop.service.BoardService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public List<ResponseBoardDto> showBoardList(){
        List<Board> boards=boardService.findAllBoard();
        List<ResponseBoardDto> boardDtos=boards.stream()
                .map(board -> new ResponseBoardDto(board))
                .collect(Collectors.toList());
        return boardDtos;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createBoard(@RequestBody RequestBoardDto dto){
        System.out.println("dto = " + dto);

        boardService.createBoard(dto);

        return ResponseEntity.ok().body("게시글 등록에 성공하였습니다");
    }

    @GetMapping("/food/{foodCategory}")
    public List<ResponseBoardDto> showBoardListByFood(@PathVariable String foodCategory){
        System.out.println("음식카테고리"+foodCategory);
        List<Board> boards=boardService.findByFoodCategory(foodCategory);
        List<ResponseBoardDto> boardDtos=boards.stream()
                .map(board -> new ResponseBoardDto(board))
                .collect(Collectors.toList());
        System.out.println("여기까지 오느라 수고했어3::"+boards);
        System.out.println("여기까지 오느라 수고했어4::"+boardDtos);
        return boardDtos;
    }

    @GetMapping("/place/{placeCategory}")
    public List<ResponseBoardDto> showBoardListByPlace(@PathVariable String placeCategory){
        System.out.println("장소카테고리"+placeCategory);
        List<Board> boards=boardService.findByPlaceCategory(placeCategory);
        List<ResponseBoardDto> boardDtos=boards.stream()
                .map(board -> new ResponseBoardDto(board))
                .collect(Collectors.toList());
        System.out.println("여기까지 오느라 수고했어5::"+boards);
        System.out.println("여기까지 오느라 수고했어6::"+boardDtos);
        return boardDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable long id, @RequestBody UpdateBoardRequest request) {
        Board updateBoard = boardService.update(id, request);

        return ResponseEntity.ok().body(updateBoard);
    }
}

