package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.domain.Participants;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.LikesDto;
import yougboyclub.honbabstop.dto.ParticipantsDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.service.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/my")
@RequiredArgsConstructor
public class MypageController {

  private final BoardService boardService;
  private final UserService userService;
  private final LikesService likesService;
//  private final ParticipantsService participantsService;

  // 내가 쓴 게시글 목록
  @GetMapping("/myBoards")
  public List<ResponseBoardDto> showMyBoard() {
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
  public List<ResponseBoardDto> showMyBoard(@PathVariable String myCategory) {
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
