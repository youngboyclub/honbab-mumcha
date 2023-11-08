package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
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
        // 파일 처리 로직(현재는 받기만 하고 있음)
        boardService.createBoard(dto);

        return ResponseEntity.ok().body("게시글 등록에 성공하였습니다");
    }

    @GetMapping("/{foodCategory}")
    public List<ResponseBoardDto> showBoardListByCategory(@PathVariable String foodCategory){
        System.out.println("음식카테고리"+foodCategory);
        List<Board> boards=boardService.findByFoodCategory(foodCategory);
        List<ResponseBoardDto> boardDtos=boards.stream()
                .map(board -> new ResponseBoardDto(board))
                .collect(Collectors.toList());
        System.out.println("여기까지 오느라 수고했어3::"+boards);
        System.out.println("여기까지 오느라 수고했어4::"+boardDtos);
        return boardDtos;
    }
}
