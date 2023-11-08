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
    private Long boardId;
    private String restaurantName;
    private String restaurantAddress;
    private String foodCategory;
    private LocalDateTime regdate;
    private String title;
    private String content;
    private User writer;
    private Long hit;
    private LocalDateTime lastModified;
    private int status;
    private int peopleLimit;



    public ResponseBoardDto(Board board){
        super();
        this.boardId=board.getId();
        this.restaurantName = board.getRestaurantName();
        this.restaurantAddress = board.getRestaurantAddress();
        this.foodCategory=board.getFoodCategory();
        this.regdate=board.getRegDate();
        this.title=board.getTitle();
        this.content=board.getContent();
        this.writer=board.getWriter();
        this.hit=board.getHit();
        this.lastModified=board.getLastModified();
        this.status=board.getStatus();
        this.peopleLimit=board.getPeopleLimit();
    }


}
