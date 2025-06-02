package org.example.projectboard.service;

import lombok.RequiredArgsConstructor;
import org.example.projectboard.domain.type.SearchType;
import org.example.projectboard.dto.ArticleWithCommentsDto;
import org.example.projectboard.dto.BoardsDto;
import org.example.projectboard.repository.BoardsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {


    private final BoardsRepository boardsRepository;

    @Transactional(readOnly = true)
    public Page<BoardsDto> searchBoards(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getBoard(Long articleId) {
        return null;
    }

    public void saveArticle(BoardsDto boards) {
    }

    public void updateArticle(BoardsDto dto) {
    }

    public void deleteArticle(long articleId) {
    }
}
