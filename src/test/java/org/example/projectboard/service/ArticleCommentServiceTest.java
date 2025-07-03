package org.example.projectboard.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.projectboard.domain.ArticleComments;
import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.UserAccount;
import org.example.projectboard.dto.ArticleCommentsDto;
import org.example.projectboard.dto.UserAccountDto;
import org.example.projectboard.repository.ArticleCommentsRepository;
import org.example.projectboard.repository.BoardsRepository;
import org.example.projectboard.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비지니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleCommentsRepository articleCommentsRepository;
    @Mock private BoardsRepository boardsRepository;
    @Mock private UserAccountRepository userAccountRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnArticleComments() {
        //Given
        Long articleId = 1L;
        ArticleComments expected = createArticleComments("content");
        given(articleCommentsRepository.findByBoards_Id(articleId)).willReturn(List.of(expected));

        //When
        List<ArticleCommentsDto> actual = sut.searchArticleComments(articleId);

        //Then
        assertThat(actual)
                .hasSize(1)
                .first().hasFieldOrPropertyWithValue("content", expected.getContent());
        then(articleCommentsRepository).should().findByBoards_Id(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        // Given
        ArticleCommentsDto dto = createArticleCommentsDto("댓글");
        given(boardsRepository.getReferenceById(dto.articleId())).willReturn(createBoards());
        given(userAccountRepository.getReferenceById(dto.userAccountDto().id())).willReturn(createUserAccount());
        given(articleCommentsRepository.save(any(ArticleComments.class))).willReturn(null);

        // When
        sut.saveArticleComment(dto);

        // Then
        then(boardsRepository).should().getReferenceById(dto.articleId());
        then(articleCommentsRepository).should().save(any(ArticleComments.class));
        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().id());
    }

    @DisplayName("댓글 저장을 시도했는데 맞는 게시글이 없으면, 경고 로그를 찍고 아무것도 안 한다.")
    @Test
    void givenNonexistentArticle_whenSavingArticleComment_thenLogsSituationAndDoesNothing() {
        // Given
        ArticleCommentsDto dto = createArticleCommentsDto("댓글");
        given(boardsRepository.getReferenceById(dto.articleId())).willThrow(EntityNotFoundException.class);

        // When
        sut.saveArticleComment(dto);

        // Then
        then(boardsRepository).should().getReferenceById(dto.articleId());
        then(userAccountRepository).shouldHaveNoInteractions();
        then(articleCommentsRepository).shouldHaveNoInteractions();
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 수정한다.")
    @Test
    void givenArticleCommentInfo_whenUpdatingArticleComment_thenUpdatesArticleComment() {
        // Given
        String oldContent = "content";
        String updatedContent = "댓글";
        ArticleComments articleComments = createArticleComments(oldContent);
        ArticleCommentsDto dto = createArticleCommentsDto(updatedContent);
        given(articleCommentsRepository.getReferenceById(dto.id())).willReturn(articleComments);

        // When
        sut.updateArticleComment(dto);

        // Then
        assertThat(articleComments.getContent())
                .isNotEqualTo(oldContent)
                .isEqualTo(updatedContent);
        then(articleCommentsRepository).should().getReferenceById(dto.id());
    }

    @DisplayName("없는 댓글 정보를 수정하려고 하면, 경고 로그를 찍고 아무 것도 안 한다.")
    @Test
    void givenNonexistentArticleComment_whenUpdatingArticleComment_thenLogsWarningAndDoesNothing() {
        // Given
        ArticleCommentsDto dto = createArticleCommentsDto("댓글");
        given(articleCommentsRepository.getReferenceById(dto.id())).willThrow(EntityNotFoundException.class);

        // When
        sut.updateArticleComment(dto);

        // Then
        then(articleCommentsRepository).should().getReferenceById(dto.id());
    }

    @DisplayName("댓글 ID를 입력하면, 댓글을 삭제한다.")
    @Test
    void givenArticleCommentId_whenDeletingArticleComment_thenDeletesArticleComment() {
        // Given
        Long articleCommentId = 1L;
        willDoNothing().given(articleCommentsRepository).deleteById(articleCommentId);

        // When
        sut.deleteArticleComment(articleCommentId);

        // Then
        then(articleCommentsRepository).should().deleteById(articleCommentId);
    }


    private ArticleCommentsDto createArticleCommentsDto(String content) {
        return ArticleCommentsDto.of(
                1L,
                1L,
                createUserAccountDto(),
                content,
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                1L,
                "uno",
                "password",
                "uno@mail.com",
                "Uno",
                "This is memo",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private ArticleComments createArticleComments(String content) {
        return ArticleComments.of(
                Boards.of(createUserAccount(), "title", "content", "hashtag"),
                createUserAccount(),
                content
        );
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                "uno",
                "password",
                "uno@email.com",
                "Uno",
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
}
