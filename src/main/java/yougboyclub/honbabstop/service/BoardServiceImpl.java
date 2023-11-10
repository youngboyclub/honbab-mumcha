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

  //모집글 작성
  @Override
  public Board createBoard(RequestBoardDto requestBoardDto) {
    Optional<User> byId = userRepository.findById(1L);


    @Override
    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }


    User user = byId.get();

    Board board = requestBoardDto.toEntity();
    board.setWriter(user);
    return boardRepository.save(board);
  }

  //모든 모집글 조회
  @Override
  public List<Board> findAllBoard() {
    return boardRepository.findAll();
  }

  @Override
  public String toString() {
    return super.toString();
  }

  //모집글의 상세 정보 조회
  public Board getBoardDetail(Long boardNo, User user) {
    //boarNo의 게시물 가져오기
    Board getBoard = boardRepository.findBoardByBoardNo(boardNo);

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


    // 내가 작성한 글 조회 메서드
    @Override
    public List<Board> findByWriter(User user) {
        return boardRepository.findByWriter(user);
    }

    @Override
    @Transactional
    public Board update(long id, UpdateBoardRequest request) {
        Board board = boardRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        board.update(request.getTitle(), request.getContent(), request.getTime(), request.getFoodCategory(), request.getPlaceCategory(), request.getPeople(), request.getRestaurantName(), request.getRestaurantAddress());
        return board;
    }
}
