package yougboyclub.honbabstop.dto;


import lombok.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseBoardDto {
    private Long board_id;
    private Long restaurant_no;
    private Long file_id;
    private String food_theme;
    private LocalDateTime regdate;
    private String title;
    private String content;
    private User writer;
    private Long hit;
    private LocalDateTime last_modified;
    private int status;
    private int people_limit;
    private int max_age;
    private int min_age;



    public ResponseBoardDto(Board board){
        super();
        this.board_id=board.getId();
        this.restaurant_no=board.getRestaurant_no();
        this.file_id=board.getFile_id();
        this.food_theme=board.getFood_theme();
        this.regdate=board.getRegDate();
        this.title=board.getTitle();
        this.content=board.getContent();
        this.writer=board.getWriter();
        this.hit=board.getHit();
        this.last_modified=board.getLast_modified();
        this.status=board.getStatus();
        this.people_limit=board.getPeople_limit();
        this.max_age=board.getMax_age();
        this.min_age=board.getMin_age();
    }


}
