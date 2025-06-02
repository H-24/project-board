package org.example.projectboard.service;

import org.example.projectboard.domain.ArticleComments;
import org.example.projectboard.domain.Boards;
import org.example.projectboard.dto.ArticleCommentsDto;
import org.example.projectboard.repository.ArticleCommentsRepository;
import org.example.projectboard.repository.BoardsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비지니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleCommentsRepository articleCommentsRepository;
    @Mock private BoardsRepository boardsRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnArticleComments() {
        //Given
        Long articleId = 1L;
        given(boardsRepository.findById(articleId)).willReturn(Optional.of(
                Boards.of("title", "content", "#java"))
        );

        //When
        List<ArticleCommentsDto> articleComments = sut.searchArticleComment(articleId);

        //Then
        assertThat(articleComments).isNotNull();
        then(articleCommentsRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        // Given
        given(articleCommentsRepository.save(any(ArticleComments.class))).willReturn(null);

        // When
        sut.saveArticleComment(ArticleCommentsDto.of(LocalDateTime.now(), "Uno", LocalDateTime.now(), "Uno", "comment"));

        // Then
        then(articleCommentsRepository).should().save(any(ArticleComments.class));
    }
}
