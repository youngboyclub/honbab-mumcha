package yougboyclub.honbabstop.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "PARTYBOARD")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "board_id"))
@ToString
public class Board extends BaseEntity{
    @Column(name = "RESTAURANT_NO")
    private Long restaurant_no;

    @Column(name = "FILE_ID")
    private Long file_id;

    @Column(name = "FOOD_THEME")
    private String food_theme;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "USER_NO")
    private User writer;

    @Column(name = "HIT")
    private Long hit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="LAST_MODIFIED")
    private LocalDate last_modified;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "PEOPLE_LIMIT")
    private int people_limit;

    @Column(name = "GENDER_LIMIT")
    private int gender_limit;

    @Column(name = "MAX_AGE")
    private int max_age;

    @Column(name = "MIN_AGE")
    private int min_age;

}
