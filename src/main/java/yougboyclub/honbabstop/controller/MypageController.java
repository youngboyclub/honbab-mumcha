package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
  private final ParticipantsService participantsService;

  // 내가 쓴 게시글 목록
  @GetMapping("/myBoards")
  public List<ResponseBoardDto> showMyBoard() {
    User userId = userService.findById(1L);
    List<Board> boards = boardService.findByWriter(userId);
    System.out.println("boards = " + boards);

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
// ------------------------구분선------------------------------------------------------

   // 마이 페이지 목록 변화에 따라 변화해야함
    @GetMapping("/board/{myCategory}")
    public List<ResponseBoardDto> selectCategory (@PathVariable String myCategory, @RequestParam String email) {
    List<ResponseBoardDto> myboard = boards.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
//        User user = userService.findById(userId);
    System.out.println("제발!! " + myboard);
    return myboard;
  }

  // 마이 페이지 목록 변화에 따라 변화해야함
//   @GetMapping("/board/{myCategory}")
//   public List<ResponseBoardDto> showMyBoard(@PathVariable String myCategory) {
//     System.out.println("myCategory = " + myCategory);
//     User userId = userService.findById(2L);
//     List<Board> boards = boardService.findByWriter(userId);
//     System.out.println("user = " + userId);

//         User findUser = userService.findByEmail(email);

//         //카테고리 설정에 따라서 출력 결과물 변화
//        if(myCategory.equals("내약속")) {
//            List<Board> myParty = boardService.findByUserNonWriter(findUser);
//            List<ResponseBoardDto> myboard = myParty.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
//             System.out.println("제발!! " + myboard);
//             return myboard;
//         } else {
//            List<Board> boards = boardService.findByWriter(findUser);
//            List<ResponseBoardDto> myboard = boards.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
//            System.out.println("제발!! " + myboard);
//            return myboard;
//        }
//     }


//     List<ResponseBoardDto> myboard = boards.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
// //        User user = userService.findById(userId);
//     System.out.println("제발!! " + myboard);
//     return myboard;
// //        }
// //        return null;
//   }

}
