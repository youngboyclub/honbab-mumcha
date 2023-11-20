package yougboyclub.honbabstop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.Likes;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.repository.LikesRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikesServiceImpl implements LikesService {
    private final LikesRepository likesRepository;

    @Override
    public List<Likes> findByUser(User user) {
        return likesRepository.findByUser(user);
    }

    @Override
    public Likes findByBoardAndUser(Board board, User user) {
        return likesRepository.findByBoardAndAndUser(board, user);
    }

    @Override
    public void createLikes(Likes likes) {
        likesRepository.save(likes);
    }

    @Override
    public void deleteLikes(Likes likes) {
        likesRepository.delete(likes);
    }

    @Override
    public List<Board> findBoardByUserLike(User user) {
        return likesRepository.findBoardByUserLike(user);
    }
}
