package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.domain.Participants;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.LikesDto;
import yougboyclub.honbabstop.dto.ParticipantsDto;
import yougboyclub.honbabstop.service.BoardService;
import yougboyclub.honbabstop.service.LikesService;
import yougboyclub.honbabstop.service.ParticipantsService;
import yougboyclub.honbabstop.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/app")
public class ApplicationController {
    private final BoardService boardService;
    private final UserService userService;
    private final ParticipantsService participantsService;
    private final LikesService likesService;


    //파티 신청하기
    @PostMapping("/participate")
    public ResponseEntity<String> createParticipants(@RequestBody ParticipantsDto participantsDto) {
        System.out.println(participantsDto);
        //유저 검색 좀더 보완, 이메일뿐만 아니라 다른 키값으로도 검색할 수 있도록
        User user = userService.findByEmail(participantsDto.getEmail());
        Board board = boardService.findById(participantsDto.getBoardNo());
        Participants participants = participantsService.findByBoardAndUser(board, user);
        System.out.println("null이냐 아니냐 그것이 문제로다1::" + participants);
        if (participants == null) {
            try {
                participants = participantsDto.toEntity();
                participants.setBoard(board);
                participants.setUser(user);
                participants.setStatus(0);
                System.out.println(participants);
                participantsService.createParticipant(participants);
                return ResponseEntity.ok("Participation created");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("서버 오류: " + e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("참가 중이거나, 이미 신청한 파티입니다.");
    }

    //파티신청 되어있는지 찾기
//    @PostMapping("/find/participants")
//    public int findParticipants(@RequestBody ParticipantsDto participantsDto) {
//        //유저 검색 좀더 보완, 이메일뿐만 아니라 다른 키값으로도 검색할 수 있도록
//        User user = userService.findByEmail(participantsDto.getEmail());
//        Board board = boardService.findById(participantsDto.getBoardNo());
//        Participants participants = participantsService.findByBoardAndUser(board, user);
//
//        //참가신청 이력이 있으면 status를 반환
//        //[1:수락 0:대기 -1:거절 -99:참여정보 없음]
//        if (participants != null) {
//            return participants.getStatus();
//        } else return -99;
//    }
    @PostMapping("/find/participants")
    public ResponseEntity<List<Participants>> findParticipants(@RequestBody ParticipantsDto participantsDto) {
        //유저 검색 좀더 보완, 이메일뿐만 아니라 다른 키값으로도 검색할 수 있도록
        User user = userService.findByEmail(participantsDto.getEmail());
        //System.out.println("zzzzzz1" + user.getId());
        List<Participants> boards = participantsService.findByUser(user);
        //System.out.println("zzzzzz2" + boards.get(0).getBoard().getId());
        //Participants participants = participantsService.findByBoardAndUser(board, user);
        return ResponseEntity.ok(boards);
        //참가신청 이력이 있으면 status를 반환
        //[1:수락 0:대기 -1:거절 -99:참여정보 없음]
    }

    @Transactional
    @PostMapping("/likes")
    public ResponseEntity<String> createLikes(@RequestBody LikesDto likesDto) {
        System.out.println(likesDto);
        //유저 검색 좀더 보완, 이메일뿐만 아니라 다른 키값으로도 검색할 수 있도록
        User user = userService.findByEmail(likesDto.getEmail());
        Board board = boardService.findById(likesDto.getBoardNo());
        Likes likes = likesService.findByBoardAndUser(board, user);
        System.out.println("null이냐 아니냐 그것이 문제로다2::" + likes);
        if (likes == null) {
            try {
                likes = likesDto.toEntity();
                likes.setBoard(board);
                likes.setUser(user);
                likesService.createLikes(likes);
                return ResponseEntity.ok("Likes created");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("서버 오류: " + e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("잘못된 요청입니다.");
    }

    @Transactional
    @PostMapping("/dislikes")
    public ResponseEntity<String> deleteLikes(@RequestBody LikesDto likesDto) {
        System.out.println(likesDto);
        //유저 검색 좀더 보완, 이메일뿐만 아니라 다른 키값으로도 검색할 수 있도록
        User user = userService.findByEmail(likesDto.getEmail());
        Board board = boardService.findById(likesDto.getBoardNo());
        Likes likes = likesService.findByBoardAndUser(board, user);
        if (likes != null) {
            try {
                likesService.deleteLikes(likes);
                return ResponseEntity.ok("Likes deleted");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("서버 오류: " + e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("잘못된 요청입니다.");

    }

    @PostMapping("/find/likes")
    public ResponseEntity<List<Likes>> findLikes(@RequestBody LikesDto likesDto) {
        //유저 검색 좀더 보완, 이메일뿐만 아니라 다른 키값으로도 검색할 수 있도록
        User user = userService.findByEmail(likesDto.getEmail());
        List<Likes> boards = likesService.findByUser(user);
        //Participants participants = participantsService.findByBoardAndUser(board, user);
        return ResponseEntity.ok(boards);
        //참가신청 이력이 있으면 status를 반환
        //[1:수락 0:대기 -1:거절 -99:참여정보 없음]
    }

    @PostMapping("/acceptparticipants")
    public ResponseEntity<String> acceptParticipants(@RequestBody ParticipantsDto participantsDto) {
        System.out.println(participantsDto.getEmail());
        System.out.println(participantsDto.getBoardNo());
        User user = userService.findByEmail(participantsDto.getEmail());
        Board board = boardService.findById(participantsDto.getBoardNo());
        Participants participants = participantsService.findByBoardAndUser(board, user);
        if (participants != null) {
            try {
                participants.setStatus(1);
                participantsService.editParticipant(participants);
                System.out.print(participants);
                return ResponseEntity.ok("Participated ");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("서버 오류: " + e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("잘못된 요청입니다.");
    }

    @PostMapping("/denyparticipants")
    public ResponseEntity<String> denyParticipants(@RequestBody ParticipantsDto participantsDto) {
        System.out.println(participantsDto.getEmail());
        System.out.println(participantsDto.getBoardNo());
        User user = userService.findByEmail(participantsDto.getEmail());
        Board board = boardService.findById(participantsDto.getBoardNo());
        Participants participants = participantsService.findByBoardAndUser(board, user);
        if (participants != null) {
            try {
                participants.setStatus(-1);
                participantsService.editParticipant(participants);
                System.out.print(participants);
                return ResponseEntity.ok("Denied ");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("서버 오류: " + e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("잘못된 요청입니다.");
    }


}

