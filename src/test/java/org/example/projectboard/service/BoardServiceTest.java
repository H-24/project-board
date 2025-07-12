package org.example.projectboard.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.UserAccount;
import org.example.projectboard.domain.constant.SearchType;
import org.example.projectboard.dto.ArticleWithCommentsDto;
import org.example.projectboard.dto.BoardsDto;
import org.example.projectboard.dto.UserAccountDto;
import org.example.projectboard.repository.BoardsRepository;
import org.example.projectboard.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

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
    @Mock private UserAccountRepository userAccountRepository;

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
        given(boardsRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());

        // When
        Page<BoardsDto> articles = sut.searchBoards(searchType, searchKeyword, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(boardsRepository).should().findByTitleContaining(searchKeyword, pageable);
    }

    @DisplayName("게시글 ID로 조회하면, 댓글 달긴 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleWithComments_thenReturnsArticleWithComments() {
        // Given
        Long articleId = 1L;
        Boards boards = createBoards();
        given(boardsRepository.findById(articleId)).willReturn(Optional.of(boards));

        // When
        ArticleWithCommentsDto dto = sut.getArticleWithComments(articleId);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", boards.getTitle())
                .hasFieldOrPropertyWithValue("content", boards.getContent())
                .hasFieldOrPropertyWithValue("hashtag", boards.getHashtag());
        then(boardsRepository).should().findById(articleId);
    }

    @DisplayName("댓글 달린 게시글이 없으면, 예외를 던진다.")
    @Test
    void givenNonexistentArticleId_whenSearchingArticleWithComments_thenThrowsException() {
        // Given
        Long articleId = 0L;
        given(boardsRepository.findById(articleId)).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.getArticleWithComments(articleId));

        // Then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("게시글이 없습니다 - articleId: " + articleId);
        then(boardsRepository).should().findById(articleId);
    }

    @DisplayName("게시글을 조회하면, 해당 게시글 반환")
    @Test
    void givenArticleId_whenGettingArticles_thenReturnsArticleList() {
        Long articleId = 1L;
        Boards boards = createBoards();
        given(boardsRepository.findById(articleId)).willReturn(Optional.of(boards));

        // When
        BoardsDto dto = sut.getBoards(articleId);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", boards.getTitle())
                .hasFieldOrPropertyWithValue("content", boards.getContent())
                .hasFieldOrPropertyWithValue("hashtag", boards.getHashtag());
        then(boardsRepository).should().findById(articleId);
    }

    @DisplayName("게시글이 없으면, 예외를 던진다.")
    @Test
    void givenNonexistentArticleId_whenSearchingArticle_thenThrowsException() {
        // Given
        Long articleId = 0L;
        given(boardsRepository.findById(articleId)).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.getArticleWithComments(articleId));

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
        given(userAccountRepository.getReferenceById(dto.userAccountDto().id())).willReturn(createUserAccount());
        given(boardsRepository.save(any(Boards.class))).willReturn(createBoards());

        //when
        sut.saveBoards(dto);

        //Then
        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().id());
        then(boardsRepository).should().save(any(Boards.class));
    }

    @DisplayName("게시글 수정 정보를 입력하면, 게시글을 수정한다")
    @Test
    void givenModifiedArticleInfo_whenUpdatingArticle_thenUpdateArticle() {
        //Given
        Boards boards = createBoards();
        BoardsDto dto = createBoardsDto("새 타이틀", "새 내용", "#springboot");
        given(boardsRepository.getReferenceById(dto.id())).willReturn(boards);
        given(userAccountRepository.getReferenceByUserId(dto.userAccountDto().userId())).willReturn(dto.userAccountDto().toEntity());

        //when
        sut.updateBoards(dto.id(), dto);

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
        sut.updateBoards(dto.id(), dto);

        // Then
        then(boardsRepository).should().getReferenceById(dto.id());
    }

    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeleteArticle() {
        //Given
        Long articleId = 1L;
        String userId = "win";
        willDoNothing().given(boardsRepository).deleteByIdAndUserAccount_UserId(articleId, userId);

        //when
        sut.deleteBoards(1L, userId);

        //Then
        then(boardsRepository).should().deleteByIdAndUserAccount_UserId(articleId, userId);
    }

    @DisplayName("게시글 수를 조회하면, 게시글 수를 반환한다")
    @Test
    void givenNothing_whenCountingArticles_thenReturnsArticleCount() {
        // GivenAdd
        long expected = 0L;
        given(boardsRepository.count()).willReturn(expected);

        // When
        long actual = sut.getBoardsCount();

        // Then
        assertThat(actual).isEqualTo(expected);
        then(boardsRepository).should().count();
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
       Boards boards = Boards.of(
                createUserAccount(),
                "title",
                "content",
                "#java"
        );
       ReflectionTestUtils.setField(boards, "id", 1L);

        return boards;
    }

    private BoardsDto createBoardsDto() {

        return createBoardsDto("title", "content", "#java");
    }

    private BoardsDto createBoardsDto(String title, String content, String hashtag) {
        return BoardsDto.of(
                1L,
                createUserAccountDto(),
                title,
                content,
                hashtag,
                LocalDateTime.now(),
                "Win",
                LocalDateTime.now(),
                "Win");
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                1L,
                "win",
                "password",
                "win@mail.com",
                "Win",
                "This is memo"
        );
    }

}
