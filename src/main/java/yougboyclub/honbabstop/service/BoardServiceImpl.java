package yougboyclub.honbabstop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.repository.BoardRepository;
import yougboyclub.honbabstop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }

    @Override
    public Board createBoard(RequestBoardDto requestBoardDto) {
        Optional<User> byId = userRepository.findById(1L);

        if (byId.isEmpty()) {
            throw new IllegalArgumentException("Invalid userId: " + 1L);
        }

        User user = byId.get();

        Board board = requestBoardDto.toEntity();
        board.setWriter(user);
        return boardRepository.save(board);
    }
}
