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
//    private Long restaurant_no;
    private String restaurantName;
    private String restaurantAddress;
//    private Long file_id;
    private String foodCategory;
    private String title;
    private String content;
    private User writer;
    private Long hit;
    private int status;
    private int peopleLimit;
//    private int max_age;
//    private int min_age;

    public RequestBoardDto(Long id) {
    }


    public Board toEntity() {
        return Board.builder()
//                .restaurant_no(restaurant_no)
                .restaurantName(restaurantName)
                .restaurantAddress(restaurantAddress)
//                .file_id(file_id)
                .foodCategory(foodCategory)
                .title(title)
                .content(content)
                .writer(writer)
                .hit(hit)
                .status(status)
                .peopleLimit(peopleLimit)
//                .max_age(max_age)
//                .min_age(min_age)
                .build();
    };
}
