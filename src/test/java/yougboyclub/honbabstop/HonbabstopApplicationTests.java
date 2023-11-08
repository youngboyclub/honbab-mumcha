package yougboyclub.honbabstop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.service.BoardService;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class HonbabstopApplicationTests {

	@Autowired
	private BoardService boardService;
	@Test
	void test_find_board_by_foodcategory() {
		List<Board> boards = boardService.findByFoodCategory("양식");
		assertNotNull(boards,"Boards ard not null");
	}

}
