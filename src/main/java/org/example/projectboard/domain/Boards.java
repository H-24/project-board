package org.example.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)      // 객체의 문자열 표현을 자동으로 생성
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})    // 인덱스 기능
@Entity
public class Boards extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserAccount userAccount;

    @Column(nullable = false, length = 255)
    private String title;   // 제목
    @Column(nullable = false, length = 10000)
    private String content; // 내용

    private String hashtag; // 해시태그

    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "boards", cascade = CascadeType.ALL)
    @RestResource(path = "article-comments")
    @ToString.Exclude
    private final Set<ArticleComments> articleComments = new LinkedHashSet<>();

    protected Boards() {}

    private Boards(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Boards of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Boards(userAccount, title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Boards boards)) return false;
        return id !=null && Objects.equals(id, boards.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
