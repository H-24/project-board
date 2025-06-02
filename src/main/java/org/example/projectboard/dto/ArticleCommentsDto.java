package org.example.projectboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.projectboard.domain.ArticleComments}
 */
public record ArticleCommentsDto(
        LocalDateTime createdAt,
        String createdBy,
        String modifiedAt,
        String modifiedBy,
        String content) implements Serializable {

  public static ArticleCommentsDto of(
          LocalDateTime createdAt,
          String createdBy,
          String modifiedAt,
          String modifiedBy,
          String content) {
    return new ArticleCommentsDto(createdAt, createdBy, modifiedAt, modifiedBy, content);
  }
}
