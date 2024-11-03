package toyPJT.toypjt_v100.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@Setter @Getter
@EntityListeners(AuditingEntityListener.class) // Auditing 활성화
public class Todo {

    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    private String content;

    private String completeYn;

    private String createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate; // 수정일자
}
