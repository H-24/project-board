package org.example.projectboard.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.type.SearchType;
import org.example.projectboard.dto.ArticleWithCommentsDto;
import org.example.projectboard.dto.BoardsDto;
import org.example.projectboard.repository.BoardsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {


    private final BoardsRepository boardsRepository;

    @Transactional(readOnly = true)
    public Page<BoardsDto> searchBoards(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isEmpty()) {
            return boardsRepository.findAll(pageable).map(BoardsDto::from);
        }
        // 검색어가 null 이거나 공백일때 페이징 쿼리(전체 게시물 목록) 반환

        return switch (searchType) {
            case TITLE -> boardsRepository.findByTitleContaining(searchKeyword, pageable).map(BoardsDto::from);
            case CONTENT -> boardsRepository.findByContentContaining(searchKeyword, pageable).map(BoardsDto::from);
            case ID -> boardsRepository.findByUserAccount_UserId(searchKeyword, pageable).map(BoardsDto::from);
            case NICKNAME -> boardsRepository.findByUserAccount_Nickname(searchKeyword, pageable).map(BoardsDto::from);
            case HASHTAG -> boardsRepository.findByHashtag(searchKeyword, pageable).map(BoardsDto::from);
        };
    }

    // 단건조회
    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getBoard(Long articleId) {
        return boardsRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveArticle(BoardsDto dto) {
        boardsRepository.save(dto.toEntity());
    }

    public void updateArticle(BoardsDto dto) {
        try {
            Boards board = boardsRepository.getReferenceById(dto.id());
            if (dto.title() != null) {
                board.setTitle(dto.title());
            }
            if (dto.content() != null) {
                board.setContent(dto.content());
            }
            board.setHashtag(dto.hashtag());
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패, 게시글을 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    public void deleteArticle(long articleId) {
        boardsRepository.deleteById(articleId);
    }
}
