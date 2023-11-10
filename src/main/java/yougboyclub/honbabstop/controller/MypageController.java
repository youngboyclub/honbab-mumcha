package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.service.BoardService;
import yougboyclub.honbabstop.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MypageController {

    private final BoardService boardService;
    private final UserService userService;


    // User에 대한 정보를 받아 Id를 조회해서 본인이 작성한 글을 조회하기 컨트롤러
    @GetMapping
    public List<ResponseBoardDto> showMyBoard () {

        User userId = userService.findById(1L);
        List<Board> boards = boardService.findByWriter(userId);
        System.out.println("boards = " + boards);

        List<ResponseBoardDto> myboard = boards.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
//        User user = userService.findById(userId);
        System.out.println("제발!! " + myboard);
        return myboard;

    }


    // 마이 페이지 목록 변화에 따라 변화해야함
    @GetMapping("/board/{myCategory}")
    public List<ResponseBoardDto> showMyBoard (@PathVariable String myCategory) {
        System.out.println("myCategory = " + myCategory);
        User userId = userService.findById(2L);
        List<Board> boards = boardService.findByWriter(userId);
        System.out.println("user = " + userId);

//        if(myCategory == "내글") {


            List<ResponseBoardDto> myboard = boards.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
//        User user = userService.findById(userId);
            System.out.println("제발!! " + myboard);
            return myboard;
//        }
//        return null;
    }
}
