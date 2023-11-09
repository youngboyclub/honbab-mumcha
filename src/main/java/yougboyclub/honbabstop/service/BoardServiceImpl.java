package yougboyclub.honbabstop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.dto.RequestBoardDto;
import yougboyclub.honbabstop.dto.UpdateBoardRequest;
import yougboyclub.honbabstop.repository.BoardRepository;
import yougboyclub.honbabstop.repository.UserRepository;
import javax.transaction.Transactional;
import java.beans.Transient;
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
    public List<Board> findByFoodCategory(String foodCategory) {
        return boardRepository.findByFoodCategory(foodCategory);
    }

    @Override
    public List<Board> findByPlaceCategory(String placeCategory) {
        return boardRepository.findByPlaceCategory(placeCategory);
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

    @Override
    @Transactional
    public Board update(long id, UpdateBoardRequest request) {
        Board board = boardRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        board.update(request.getTitle(), request.getContent(), request.getTime(), request.getFoodCategory(), request.getPlaceCategory(), request.getPeople(), request.getRestaurantName(), request.getRestaurantAddress());
        return board;
    }
}
