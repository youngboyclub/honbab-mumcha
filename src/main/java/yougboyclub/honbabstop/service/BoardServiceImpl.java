package yougboyclub.honbabstop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }


    @Override
    public Board createBoard(RequestBoardDto requestBoardDto) {
        Board board = requestBoardDto.toEntity();
        return boardRepository.save(board);
    }

    // 내가 작성한 글 조회 메서드
    @Override
    public List<Board> findByWriter(User user) {
        return boardRepository.findByWriter(user);
    }


}
