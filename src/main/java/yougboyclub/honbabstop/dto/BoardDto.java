package yougboyclub.honbabstop.dto;

import lombok.*;
import yougboyclub.honbabstop.domain.Board;
import yougboyclub.honbabstop.domain.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDto {
    private Long restaurant_no;
    private Long file_id;
    private String food_theme;
    private LocalDate reg_date;
    private String title;
    private String content;
    private User writer;
    private Long hit;
    private LocalDate last_modified;

    @Builder
    public Board toEntity() {
        Board board = Board.builder()
                .restaurant_no(restaurant_no)
                .file_id(file_id)
                .food_theme(food_theme)
                .reg_date(reg_date)
                .title(title)
                .content(content)
                .writer(writer)
                .hit(hit)
                .last_modified(last_modified)
                .build();
        return board;
    };
}
