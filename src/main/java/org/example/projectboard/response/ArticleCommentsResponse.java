package org.example.projectboard.response;

import org.example.projectboard.dto.ArticleCommentsDto;

import java.time.LocalDateTime;

public record ArticleCommentsResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String email,
        String nickname
) {

    public static ArticleCommentsResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleCommentsResponse(id, content, createdAt, email, nickname);
    }

    public static ArticleCommentsResponse from(ArticleCommentsDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new ArticleCommentsResponse(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname
        );
    }

}
