package org.example.projectboard.dto;

import org.example.projectboard.domain.ArticleComments;
import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.UserAccount;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.projectboard.domain.ArticleComments}
 */
public record ArticleCommentsDto(
        Long id,
        Long articleId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {

  public static ArticleCommentsDto of(Long articleId, UserAccountDto userAccountDto, String content) {

    return new ArticleCommentsDto(null, articleId, userAccountDto, content, null, null, null, null);
  }

  public static ArticleCommentsDto of(
          Long id,
          Long articleId,
          UserAccountDto userAccountDto,
          String content,
          LocalDateTime createdAt,
          String createdBy,
          LocalDateTime modifiedAt,
          String modifiedBy) {
    return new ArticleCommentsDto(id, articleId, userAccountDto, content, createdAt, createdBy, modifiedAt, modifiedBy);
  }

  public static ArticleCommentsDto from(ArticleComments entity) {
    return new ArticleCommentsDto(
            entity.getId(),
            entity.getBoards().getId(),
            UserAccountDto.from(entity.getUserAccount()),
            entity.getContent(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy()
            );
  }

  public ArticleComments toEntity(Boards boards, UserAccount userAccount) {
    return ArticleComments.of(
            boards,
            userAccount,
            content
    );
  }

}
