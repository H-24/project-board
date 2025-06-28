package org.example.projectboard.response;

import org.example.projectboard.dto.BoardsDto;

import java.time.LocalDateTime;

public record BoardsResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String email,
        String nickname
) {

    public static BoardsResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname) {
        return new BoardsResponse(id, title, content, hashtag, createdAt, email, nickname);
    }

    public static BoardsResponse from(BoardsDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new BoardsResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname
        );
    }

}
