package org.example.projectboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.projectboard.domain.Boards}
 */
public record BoardsUpdateDto(
        String title,
        String content,
        String hashtag) implements Serializable {

    public static BoardsUpdateDto of(
            String title,
            String content,
            String hashtag) {
        return new BoardsUpdateDto( title, content, hashtag);
    }
}
