package yougboyclub.honbabstop.dto;


import lombok.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestBoardDto {
    private Long restaurant_no;
    private Long file_id;
    private String food_theme;
    private String title;
    private String content;
    private User writer;
    private Long hit;
    private LocalDate last_modified;
    private int status;
    private int people_limit;
    private int gender_limit;
    private int max_age;
    private int min_age;

    public RequestBoardDto(Long id) {
    }

    @Builder
    public Board toEntity() {
        Board board = Board.builder()
                .restaurant_no(restaurant_no)
                .file_id(file_id)
                .food_theme(food_theme)
                .title(title)
                .content(content)
                .writer(writer)
                .hit(hit)
                .last_modified(last_modified)
                .status(status)
                .people_limit(people_limit)
                .gender_limit(gender_limit)
                .max_age(max_age)
                .min_age(min_age)
                .build();
        return board;
    };
}
