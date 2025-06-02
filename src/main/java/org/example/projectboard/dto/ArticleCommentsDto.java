package org.example.projectboard.dto;

import org.example.projectboard.domain.ArticleComments;
import org.example.projectboard.domain.Boards;

import java.io.Serializable;
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
        String modifiedBy) implements Serializable {

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

  public ArticleComments toEntity(Boards entity) {
    return ArticleComments.of(
            entity,
            userAccountDto.toEntity(),
            content
    );
  }

}
