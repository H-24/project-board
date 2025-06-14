package org.example.projectboard.dto;

import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.projectboard.domain.Boards}
 */
public record BoardsDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) implements Serializable {
    public static BoardsDto of(
            Long id,
            UserAccountDto userAccountDto,
            String title,
            String content,
            String hashtag,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy) {
        return new BoardsDto(id, userAccountDto, title, content, hashtag, createdAt, createdBy, modifiedAt,modifiedBy);
    }

    public static BoardsDto from(Boards entity) {
        return new BoardsDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    // dto 정보로 entity를 새로 하나 생성해서 저장
    public Boards toEntity() {
        return Boards.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }
}
