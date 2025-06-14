package org.example.projectboard.controller;

import org.example.projectboard.config.SecurityConfig;
import org.example.projectboard.dto.ArticleWithCommentsDto;
import org.example.projectboard.dto.UserAccountDto;
import org.example.projectboard.service.BoardService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(BoardsController.class) // 슬라이스 테스트(특정 계층만 테스트-여기서는 controller)
class BoardsControllerTest {

    private final MockMvc mvc;

    @MockBean private BoardService boardService;

    public BoardsControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view] [GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingBoardsView_thenReturnsBoardsView() throws Exception {
        // Given
        given(boardService.searchBoards(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
        // When & Then
        mvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/index"))
                .andExpect(model().attributeExists("boards"));
        then(boardService).should().searchBoards(eq(null), eq(null), any(Pageable.class));
    }

    @DisplayName("[view] [GET] 게시글 리스트 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingBoardView_thenReturnsBoardView() throws Exception {
        // Given
        Long boardId = 1L;
        given(boardService.getBoard(boardId)).willReturn(createArticleWithCommentsDto());
        // When & Then
        mvc.perform(get("/boards/" + boardId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/details"))
                .andExpect(model().attributeExists("boards"));
        then(boardService).should().getBoard(boardId);
    }

    @Disabled("구현중")
    @DisplayName("[view] [GET] 게시글 리스트 검색 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingBoardSearchView_thenReturnsBoardSearchView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/boards/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/search"));
    }

    @Disabled("구현중")
    @DisplayName("[view] [GET] 게시글 리스트 해시태그 검색 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingBoardsHashtagSearchView_thenReturnsBoardsHashtagSearchView()
            throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/boards/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/search-hashtag"));    }

    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "#java",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(1L,
                "uno",
                "pw",
                "uno@mail.com",
                "Uno",
                "memo",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }
}
