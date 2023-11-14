package yougboyclub.honbabstop;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;
import yougboyclub.honbabstop.repository.BoardRepository;
import yougboyclub.honbabstop.service.BoardService;
import yougboyclub.honbabstop.service.UserService;

import java.util.Date;
import java.util.List;


import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class HonbabstopApplicationTests {

	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;

	@Test
	void test_find_board_by_foodcategory() {
		List<Board> boards = boardService.findByFoodCategory("양식");
		assertNotNull(boards,"Boards ard not null");
	}

	@Test
	@DisplayName("회원 아이디에 맞는 게시글 가져오기")
	public void findByMyBoard() {
		User user = userService.findById(1L);
		List<Board> boards = boardService.findByWriter(user);
		System.out.println("boards = " + boards);
	}

	@Test
	@DisplayName("참가한 파티 조회")
	public void myParty() {
		User user = userService.findById(1L);
		List<Board> boards = boardService.findByUserNonWriter(user);
		System.out.println("boards = " + boards);
	}
}
