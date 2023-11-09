package yougboyclub.honbabstop.dto;

import lombok.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Participants;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDate;
import java.util.stream.Collectors;
/**
* 참가 신청을 넣은 유저에게 어떤 정보를 Response?
* 게시글에 띄울 정보는 어떤거? --> 게시글엔 참가 신청이 수락된 사람만
*
* */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class ResponseParticipantsDto {
  private Long participantsNo;  //참가 번호
  private Long boardNo;         //해당 게시글 번호
  private String userName;      //신청자 이름
  private String gender;        //신청자 성별
  private LocalDate birth;      //신청자 생년월일
  private int status;           //신청자 상태
}
