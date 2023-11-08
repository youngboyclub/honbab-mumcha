package yougboyclub.honbabstop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.Board;
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
    public List<Board> findByFoodCategory(String foodCategory) {
        return boardRepository.findByFoodCategory(foodCategory);
    }

    @Override
    public Board createBoard(RequestBoardDto requestBoardDto) {
        Board board = requestBoardDto.toEntity();
        return boardRepository.save(board);
    }
}
