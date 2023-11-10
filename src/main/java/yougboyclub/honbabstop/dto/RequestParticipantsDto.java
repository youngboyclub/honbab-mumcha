package yougboyclub.honbabstop.dto;

import lombok.*;

import java.time.LocalDate;

/**
* 유저는 서버에게 어떤 정보를 Request?
* 게시글에 띄울 정보는 어떤거? --> 게시글엔 참가 신청이 수락된 사람만
*
* */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class RequestParticipantsDto {
  private Long participantsNo;  //참가 번호
  private Long boardNo;         //해당 게시글 번호
  private String userName;      //신청자 이름
  private String gender;        //신청자 성별
  private LocalDate birth;      //신청자 생년월일
  private int status;           //신청자 상태
}
