package org.example.projectboard.dto.request;

import org.example.projectboard.dto.BoardsDto;
import org.example.projectboard.dto.UserAccountDto;

public record BoardsRequest (String title, String content, String hashtag) {

    public static BoardsRequest of(String title, String content, String hashtag) {

        return new BoardsRequest(title, content, hashtag);
    }

    public BoardsDto toDto(UserAccountDto userAccountDto) {
        return BoardsDto.of(
                userAccountDto,
                title,
                content,
                hashtag
        );
    }
}
