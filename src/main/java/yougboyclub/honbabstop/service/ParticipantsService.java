package yougboyclub.honbabstop.service;

import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Participants;
import yougboyclub.honbabstop.domain.User;

import java.util.List;

public interface ParticipantsService {
    List<Participants> findByUser(User user);

    void createParticipant(Participants participants);

    Participants findByBoardAndUser(Board board, User user);
    
}
