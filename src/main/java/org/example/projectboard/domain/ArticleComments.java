package org.example.projectboard.domain;

import java.time.LocalDateTime;

public class ArticleComments {
    private Long id;
    private Boards boards;  // 게시글 (ID)
    private String content; // 내용

    private LocalDateTime createdAt;    // 생성일시
    private String createdBy;           // 생성자
    private LocalDateTime modifiedAt;   // 수정일시
    private String modifiedBy;          // 수정자
}
