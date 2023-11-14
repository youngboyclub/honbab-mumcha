package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/api/my")
@RequiredArgsConstructor
public class MypageController {

    private final BoardService boardService;
    private final UserService userService;


    // User에 대한 정보를 받아 Id를 조회해서 본인이 작성한 글을 조회하기 컨트롤러
    //RequestParm으로 해보기
    @GetMapping
    public List<ResponseBoardDto> showMyBoard (@RequestParam String email) {
        //get요청으로 받아오면 param으로 넘겨 받아야함

        User userId = userService.findByEmail(email);
        List<Board> party = boardService.findByUser(userId);
        System.out.println("party = " + party);
        System.out.println("party = " + party.size());

        List<ResponseBoardDto> myboard = party.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
//        User user = userService.findById(userId);
        //System.out.println("제발!! " + myboard);
        return myboard;
    }


    // 마이 페이지 목록 변화에 따라 변화해야함
    @GetMapping("/board/{myCategory}")
    public List<ResponseBoardDto> selectCategory (@PathVariable String myCategory, @RequestParam String email) {

        User findUser = userService.findByEmail(email);

        //카테고리 설정에 따라서 출력 결과물 변화
        if(myCategory.equals("내약속")) {
            List<Board> myParty = boardService.findByUserNonWriter(findUser);
            List<ResponseBoardDto> myboard = myParty.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
            System.out.println("제발!! " + myboard);
            return myboard;
        } else {
            List<Board> boards = boardService.findByWriter(findUser);
            List<ResponseBoardDto> myboard = boards.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
            System.out.println("제발!! " + myboard);
            return myboard;
        }
    }
}
