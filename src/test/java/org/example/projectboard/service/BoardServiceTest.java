package org.example.projectboard.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.UserAccount;
import org.example.projectboard.domain.type.SearchType;
import org.example.projectboard.dto.ArticleWithCommentsDto;
import org.example.projectboard.dto.BoardsDto;
import org.example.projectboard.dto.UserAccountDto;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비지니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks private BoardService sut;
    @Mock private BoardsRepository boardsRepository;


    @DisplayName("검색어 없이 게시글을 검색하면, 게시글 페이지를 반환한다")
    @Test
    void givenNoSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        //Given
        Pageable pageable = Pageable.ofSize(20);
        given(boardsRepository.findAll(pageable)).willReturn(Page.empty());

        //When
        Page<BoardsDto> boards = sut.searchBoards(null, null, pageable);

        //Then
        assertThat(boards).isEmpty();
        then(boardsRepository).should().findAll(pageable);
    }

    @DisplayName("검색어와 함께 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        // Given
        SearchType searchType = SearchType.TITLE;
        String searchKeyword = "title";
        Pageable pageable = Pageable.ofSize(20);
        given(boardsRepository.findByTitle(searchKeyword, pageable)).willReturn(Page.empty());

        // When
        Page<BoardsDto> articles = sut.searchBoards(searchType, searchKeyword, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(boardsRepository).should().findByTitle(searchKeyword, pageable);
    }

    @DisplayName("게시글을 조회하면, 해당 게시글 반환")
    @Test
    void givenArticleId_whenGettingArticles_thenReturnsArticleList() {
        Long articleId = 1L;
        Boards boards = createBoards();
        given(boardsRepository.findById(articleId)).willReturn(Optional.of(boards));

        // When
        ArticleWithCommentsDto dto = sut.getBoard(articleId);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", boards.getTitle())
                .hasFieldOrPropertyWithValue("content", boards.getContent())
                .hasFieldOrPropertyWithValue("hashtag", boards.getHashtag());
        then(boardsRepository).should().findById(articleId);
    }

    @DisplayName("없는 게시글을 조회하면, 예외를 던진다.")
    @Test
    void givenNonexistentArticleId_whenSearchingArticle_thenThrowsException() {
        // Given
        Long articleId = 0L;
        given(boardsRepository.findById(articleId)).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.getBoard(articleId));

        // Then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("게시글이 없습니다 - articleId: " + articleId);
        then(boardsRepository).should().findById(articleId);
    }

    @DisplayName("게시글을 작성하면, 게시글을 생성한다")
    @Test
    void givenArticleInfo_whenPostingArticle_thenSaveArticle() {
        //Given
        BoardsDto dto = createBoardsDto();
        given(boardsRepository.save(any(Boards.class))).willReturn(createBoards());

        //when
        sut.saveArticle(dto);

        //Then
        then(boardsRepository).should().save(any(Boards.class));
    }

    @DisplayName("게시글 수정 정보를 입력하면, 게시글을 수정한다")
    @Test
    void givenModifiedArticleInfo_whenUpdatingArticle_thenUpdateArticle() {
        //Given
        Boards boards = createBoards();
        BoardsDto dto = createBoardsDto("새 타이틀", "새 내용", "#springboot");
        given(boardsRepository.getReferenceById(dto.id())).willReturn(boards);

        //when
        sut.updateArticle(dto);

        //Then
        assertThat(boards)
                .hasFieldOrPropertyWithValue("title", dto.title())
                .hasFieldOrPropertyWithValue("content", dto.content())
                .hasFieldOrPropertyWithValue("hashtag", dto.hashtag());
        then(boardsRepository).should().getReferenceById(dto.id());
    }

    @DisplayName("없는 게시글의 수정 정보를 입력하면, 경고 로그를 찍고 아무 것도 하지 않는다.")
    @Test
    void givenNonexistentArticleInfo_whenUpdatingArticle_thenLogsWarningAndDoesNothing() {
        // Given
        BoardsDto dto = createBoardsDto("새 타이틀", "새 내용", "#springboot");
        given(boardsRepository.getReferenceById(dto.id())).willThrow(EntityNotFoundException.class);

        // When
        sut.updateArticle(dto);

        // Then
        then(boardsRepository).should().getReferenceById(dto.id());
    }

    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeleteArticle() {
        //Given
        Long articleId = 1L;
        willDoNothing().given(boardsRepository).deleteById(articleId);

        //when
        sut.deleteArticle(1L);

        //Then
        then(boardsRepository).should().deleteById(articleId);
    }


    private UserAccount createUserAccount() {
        return UserAccount.of(
                "win",
                "password",
                "win@email.com",
                "Win",
                null
        );
    }

    private Boards createBoards() {
        return Boards.of(
                createUserAccount(),
                "title",
                "content",
                "#java"
        );
    }

    private BoardsDto createBoardsDto() {
        return createBoardsDto("title", "content", "#java");
    }

    private BoardsDto createBoardsDto(String title, String content, String hashtag) {
        return BoardsDto.of(1L,
                createUserAccountDto(),
                title,
                content,
                hashtag,
                LocalDateTime.now(),
                "Uno",
                LocalDateTime.now(),
                "Uno");
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                1L,
                "win",
                "password",
                "win@mail.com",
                "Win",
                "This is memo",
                LocalDateTime.now(),
                "win",
                LocalDateTime.now(),
                "win"
        );
    }

}
