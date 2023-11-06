package yougboyclub.honbabstop.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) // 엔티티 객체가 생성/변경되는 것을 감지하는 역할 (등록일, 수정일 등에 사용.)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate //JPA에서 엔티티의 생성 시간을 처리한다.
    @Column(name = "reg_date", updatable = false) //updatable = false => 생성시간을 변경하지 않음.
    private LocalDateTime regDate;

    public BaseEntity(Long id, LocalDateTime regDate) {
        this.id = id;
        this.regDate = regDate;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public void setId(Long id) {
        this.id = id;
    }

}