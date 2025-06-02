package org.example.projectboard.service;

import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.type.SearchType;
import org.example.projectboard.dto.BoardsDto;
import org.example.projectboard.dto.BoardsUpdateDto;
import org.example.projectboard.repository.BoardsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비지니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks private BoardService sut;
    @Mock private BoardsRepository boardsRepository;


    @DisplayName("게시글을 검색하면, 게시글 리스트 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        //Given

        //When
        Page<BoardsDto> boards = sut.searchBoards(SearchType.TITLE, "search keyword");

        //Then
        assertThat(boards).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 해당 게시글 반환")
    @Test
    void givenArticleId_whenGettingArticles_thenReturnsArticleList() {
        //Given

        //When
        BoardsDto boards = sut.searchBoard(1L);

        //Then
        assertThat(boards).isNotNull();
    }

    @DisplayName("게시글을 작성하면, 게시글을 생성한다")
    @Test
    void givenArticleInfo_whenPostingArticle_thenSaveArticle() {
        //Given
        given(boardsRepository.save(any(Boards.class))).willReturn(null);

        //when
        sut.saveArticle(BoardsDto.of(LocalDateTime.now(), "Win", "title",
                "content", "#java"));

        //Then
        then(boardsRepository).should().save(any(Boards.class));
    }

    @DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 수정한다")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdateArticle() {
        //Given
        given(boardsRepository.save(any(Boards.class))).willReturn(null);

        //when
        sut.updateArticle(1L, BoardsUpdateDto.of("title",
                "content", "#java"));

        //Then
        then(boardsRepository).should().save(any(Boards.class));
    }

    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeleteArticle() {
        //Given
        willDoNothing().given(boardsRepository).delete(any(Boards.class));

        //when
        sut.deleteArticle(1L);

        //Then
        then(boardsRepository).should().delete(any(Boards.class));
    }
}
