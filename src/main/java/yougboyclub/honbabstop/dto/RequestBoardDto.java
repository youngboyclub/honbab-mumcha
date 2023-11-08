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
@Builder
public class RequestBoardDto {
    private String restaurantName;
    private String restaurantAddress;
    private String foodCategory;
    private String title;
    private String content;
    private User writer;
    private Long hit;
    private int status;
    private int peopleLimit;

    public RequestBoardDto(Long id) {
    }


    public Board toEntity() {
        return Board.builder()
                .restaurantName(restaurantName)
                .restaurantAddress(restaurantAddress)
                .foodCategory(foodCategory)
                .title(title)
                .content(content)
                .writer(writer)
                .hit(hit)
                .status(status)
                .peopleLimit(peopleLimit)
                .build();
    };
}
