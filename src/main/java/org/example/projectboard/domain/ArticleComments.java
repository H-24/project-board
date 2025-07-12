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
@ToString(callSuper = true)     // 디버깅시 필요, 출력시 부모필드 사용가능
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
    @ManyToOne(optional = false) private UserAccount userAccount;
    @Column(nullable = false, length = 500) private String content; // 내용

    protected ArticleComments() {}

    private ArticleComments(Boards boards, UserAccount userAccount, String content) {
        this.boards = boards;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static ArticleComments of(Boards boards, UserAccount userAccount, String content) {
        return new ArticleComments(boards, userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComments that)) return false;
        return id != null && Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
