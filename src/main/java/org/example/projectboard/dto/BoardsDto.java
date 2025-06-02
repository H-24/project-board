package org.example.projectboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.projectboard.domain.Boards}
 */
public record BoardsDto(
        LocalDateTime createdAt,
        String createdBy,
        String title,
        String content,
        String hashtag) implements Serializable {

    public static BoardsDto of(
            LocalDateTime createdAt,
            String createdBy,
            String title,
            String content,
            String hashtag) {
        return new BoardsDto(createdAt, createdBy, title, content, hashtag);
    }
}
