package org.example.projectboard.service;

import lombok.RequiredArgsConstructor;
import org.example.projectboard.domain.type.SearchType;
import org.example.projectboard.dto.BoardsDto;
import org.example.projectboard.dto.BoardsUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    @Transactional(readOnly = true)
    public Page<BoardsDto> searchBoards(SearchType searchType, String searchKeyword) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public BoardsDto searchBoard(long l) {
        return null;
    }

    public void saveArticle(BoardsDto boards) {
    }

    public void updateArticle(long l, BoardsUpdateDto boardsUpdate) {
    }

    public void deleteArticle(long l) {
    }
}
