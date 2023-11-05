package yougboyclub.honbabstop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "PARTYBOARD")
@Entity
public class Board extends BaseEntity{
    @Column(name = "RESTAURANT_NO")
    private Long restaurant_no;

    @Column(name = "FILE_ID")
    private Long file_id;

    @Column(name = "FOOD_THEME")
    private String food_theme;

    @Column(name = "REG_DATE")
    private LocalDate reg_date;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_NO")
    @Column(name = "WRITER")
    private User writer;

    @Column(name = "HIT")
    private Long hit;

    @Column(name="LAST_MODIFIED")
    private LocalDate last_modified;


}
