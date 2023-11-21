package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.LikesDto;
import yougboyclub.honbabstop.dto.ParticipantsUserInfoDto;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.ResponseBoardDto;
import yougboyclub.honbabstop.service.BoardService;
import yougboyclub.honbabstop.service.LikesService;
import yougboyclub.honbabstop.service.ParticipantsService;
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
    private final ParticipantsService participantsService;
    private final LikesService likesService;


    // User에 대한 정보를 받아 Id를 조회해서 본인이 작성한 글을 조회하기 컨트롤러
    //RequestParm으로 해보기
    @GetMapping
    public List<ResponseBoardDto> showMyBoard (@RequestParam String email) {
        System.out.println("이거 왜 안찍혀");
        //get요청으로 받아오면 param으로 넘겨 받아야함
        System.out.println("이메일 들어와? = " + email);

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
        System.out.println("카테고리 작성돼? = " + myCategory);
        System.out.println("이메일 들어와? = " + email);
        User findUser = userService.findByEmail(email);

        //카테고리 설정에 따라서 출력 결과물 변화
        if(myCategory.equals("내약속")) {
            List<Board> myParty = boardService.findByUserNonWriter(findUser);
            List<ResponseBoardDto> myboard = myParty.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
            System.out.println("제발!! " + myboard);
            return myboard;
        } else if (myCategory.equals("내가찜한약속")) {
            List<Board> myLike = likesService.findBoardByUserLike(findUser);
            List<ResponseBoardDto> myboard = myLike.stream().map(ResponseBoardDto::new).collect(Collectors.toList());
            return myboard;
        } else{
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