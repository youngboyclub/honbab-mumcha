package yougboyclub.honbabstop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Participants;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.repository.ParticipantsRepository;
import yougboyclub.honbabstop.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParticipantsServiceImpl implements ParticipantsService {

  private final ParticipantsRepository participantsRepository;


  @Override
  public List<Participants> findByUser(User user) {
    return null;
  }

  @Override
  public List<User> findByBoardPartyUser(Board board) {
    return participantsRepository.findByBoardPartyUser(board);
  }
}
