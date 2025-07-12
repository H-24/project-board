package org.example.projectboard.repository;

import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@DataJpaTest
public class JpaRepositoryTest {

    private final BoardsRepository boardsRepository;
    private final ArticleCommentsRepository articleCommentsRepository;
    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(
            @Autowired BoardsRepository boardsRepository,
            @Autowired ArticleCommentsRepository articleCommentsRepository,
            @Autowired UserAccountRepository userAccountRepository
    ) {
        this.boardsRepository = boardsRepository;
        this.articleCommentsRepository = articleCommentsRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<Boards> boards = boardsRepository.findAll();

        // Then
        assertThat(boards)
                .isNotNull()
                .hasSize(72);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousCount = boardsRepository.count();
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("newWin", "pw", null, null, null));
        Boards boards = Boards.of(userAccount, "new article", "new content", "#winter");

        // When
        boardsRepository.save(boards);

        // Then
        assertThat(boardsRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Boards boards = boardsRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        boards.setHashtag(updatedHashtag);

        // When
        Boards savedArticle = boardsRepository.saveAndFlush(boards);

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Boards boards = boardsRepository.findById(1L).orElseThrow();
        long previousBoardsCount = boardsRepository.count();
        long previousArticleCommentsCount = articleCommentsRepository.count();
        int deletedCommentsSize = boards.getArticleComments().size();

        // When
        boardsRepository.delete(boards);

        // Then
        assertThat(boardsRepository.count()).isEqualTo(previousBoardsCount - 1);
        assertThat(articleCommentsRepository.count()).isEqualTo(previousArticleCommentsCount - deletedCommentsSize);
    }

    @EnableJpaAuditing
    @TestConfiguration
    public static class TestJpaConfig {
        @Bean
        public AuditorAware<String> auditorAware() {
            return () -> Optional.of("win");
        }
    }

}
