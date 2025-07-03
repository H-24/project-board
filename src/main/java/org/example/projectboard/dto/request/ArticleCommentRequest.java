package org.example.projectboard.dto.request;

import org.example.projectboard.dto.ArticleCommentsDto;
import org.example.projectboard.dto.UserAccountDto;

public record ArticleCommentRequest (Long articleId, String content){

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentsDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentsDto.of(
                articleId,
                userAccountDto,
                content
        );
    }
}
