package yougboyclub.honbabstop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Participants;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.ParticipantsDto;
import yougboyclub.honbabstop.service.BoardService;
import yougboyclub.honbabstop.service.LikesService;
import yougboyclub.honbabstop.service.ParticipantsService;
import yougboyclub.honbabstop.service.UserService;

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
    @PostMapping("/find/participants")
    public int findParticipants(@RequestBody ParticipantsDto participantsDto) {
        System.out.println(participantsDto);
        //유저 검색 좀더 보완, 이메일뿐만 아니라 다른 키값으로도 검색할 수 있도록
        User user = userService.findByEmail(participantsDto.getEmail());
        Board board = boardService.findById(participantsDto.getBoardNo());
        Participants participants = participantsService.findByBoardAndUser(board, user);
        System.out.println("null이냐 아니냐 그것이 문제로다2::" + participants);

        //참가신청 이력이 있으면 status를 반환
        //[1:수락 0:대기 -1:거절 -99:참여정보 없음]
        if (participants != null) {
            return participants.getStatus();
        } else return -99;
    }

    ;

}

