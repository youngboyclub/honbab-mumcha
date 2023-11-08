package yougboyclub.honbabstop.dto;


import lombok.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;

import java.sql.Time;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseBoardDto {
    private Long boardId;
    private User writer;
    private String title;
    private String content;
    private Time time;
    private String foodCategory;
    private String placeCategory;
    private int status;
    private int people;
    private String restaurantName;
    private String restaurantAddress;
    private LocalDateTime regDate;
    private Long hit;
    private LocalDateTime lastModified;



    public ResponseBoardDto(Board board){
        super();
        this.boardId=board.getId();
        this.writer=board.getWriter();
        this.title=board.getTitle();
        this.content=board.getContent();
        this.time=board.getTime();
        this.foodCategory=board.getFoodCategory();
        this.placeCategory=board.getPlaceCategory();
        this.status=board.getStatus();
        this.people=board.getPeople();
        this.restaurantName = board.getRestaurantName();
        this.restaurantAddress = board.getRestaurantAddress();
        this.regDate=board.getRegDate();
        this.hit=board.getHit();
        this.lastModified=board.getLastModified();
    }


}
