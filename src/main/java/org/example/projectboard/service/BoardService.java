package org.example.projectboard.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.UserAccount;
import org.example.projectboard.domain.constant.SearchType;
import org.example.projectboard.dto.ArticleWithCommentsDto;
import org.example.projectboard.dto.BoardsDto;
import org.example.projectboard.repository.BoardsRepository;
import org.example.projectboard.repository.UserAccountRepository;
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
    private final UserAccountRepository userAccountRepository;

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
    public ArticleWithCommentsDto getArticleWithComments(Long articleId) {
        return boardsRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    @Transactional(readOnly = true)
    public BoardsDto getBoards(Long articleId) {
        return boardsRepository.findById(articleId)
                .map(BoardsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveBoards(BoardsDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceByUserId(dto.userAccountDto().userId());
        boardsRepository.save(dto.toEntity(userAccount));
    }

    public void updateBoards(Long articleId, BoardsDto dto) {
        try {
            Boards board = boardsRepository.getReferenceById(articleId);
            UserAccount userAccount = userAccountRepository.getReferenceByUserId(dto.userAccountDto().userId());
            if (board.getUserAccount().equals(userAccount)) {
                if (dto.title() != null) { board.setTitle(dto.title()); }
                if (dto.content() != null) { board.setContent(dto.content()); }
                board.setHashtag(dto.hashtag());
            }
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패, 게시글을 수정하는데 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }

    public void deleteBoards(long articleId, String userId) {
        boardsRepository.deleteByIdAndUserAccount_UserId(articleId, userId);
    }

    public long getBoardsCount() {
        return boardsRepository.count();
    }
}
