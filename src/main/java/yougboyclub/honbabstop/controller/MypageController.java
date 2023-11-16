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
import yougboyclub.honbabstop.dto.ParticipantsUserInfoDto;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.service.BoardService;
import yougboyclub.honbabstop.service.ParticipantsService;
import yougboyclub.honbabstop.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/my")
@RequiredArgsConstructor
public class MypageController {

  private final BoardService boardService;
  private final UserService userService;
  private final ParticipantsService participantsService;


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

    @GetMapping("/party/{id}")
    public List<ParticipantsUserInfoDto> myPartyUser(@PathVariable Long id) {
        System.out.println("글 번호 = " + id);
        Board partyBoard = boardService.findById(id);

        System.out.println("내가 참여한 글 = " + partyBoard);
        List<User> partyUser = participantsService.findByBoardPartyUser(partyBoard);
        List<ParticipantsUserInfoDto> userInfo = partyUser.stream().map(ParticipantsUserInfoDto::new).collect(Collectors.toList());

        return userInfo;
    }
}
