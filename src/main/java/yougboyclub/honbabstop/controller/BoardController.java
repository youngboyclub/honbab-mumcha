package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;
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
        //ModelAndView mov = new ModelAndView("/boardList");
        List<Board> boards=boardService.findAllBoard();
        List<ResponseBoardDto> boardDtos=boards.stream()
                .map(board -> new ResponseBoardDto(board))
                .collect(Collectors.toList());
        System.out.println("여기까지 오느라 수고했어1::"+boards);
        System.out.println("여기까지 오느라 수고했어2::"+boardDtos);
        //mov.addObject("boardDtos",boardDtos);
        return boardDtos;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createBoard(@RequestBody RequestBoardDto dto){
        System.out.println("dto = " + dto);

        boardService.createBoard(dto);

        return ResponseEntity.ok().body("게시글 등록에 성공하였습니다");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable long id, @RequestBody UpdateBoardRequest request) {
        Board updateBoard = boardService.update(id, request);

        return ResponseEntity.ok().body(updateBoard);
    }

}
