package org.example.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.stereotype.Indexed;
//
//import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class ArticleComments extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Boards boards;  // 게시글 (ID)
    @Column(nullable = false, length = 500) private String content; // 내용

    protected ArticleComments() {}

    private ArticleComments(Boards boards, String content) {
        this.boards = boards;
        this.content = content;
    }

    public static ArticleComments of(Boards boards, String content) {
        return new ArticleComments(boards, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComments that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
