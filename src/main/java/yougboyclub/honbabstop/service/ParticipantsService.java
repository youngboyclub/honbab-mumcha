package yougboyclub.honbabstop.service;

import org.springframework.data.repository.query.Param;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Participants;
import yougboyclub.honbabstop.domain.User;

import java.util.List;

public interface ParticipantsService {

  List<Participants> findByUser(User user);

  List<User> findByBoardPartyUser(Board board);

  void createParticipant(Participants participants);

  Participants findByBoardAndUser(Board board, User user);

}
