package org.example.projectboard.controller;

import org.example.projectboard.config.SecurityConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(BoardsController.class)
class BoardsControllerTest {

    private final MockMvc mvc;

    public BoardsControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view] [GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingBoardsView_thenReturnsBoardsView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/index"))
                .andExpect(model().attributeExists("boards"));
    }

    @DisplayName("[view] [GET] 게시글 리스트 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingBoardView_thenReturnsBoardView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/boards/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/details"))
                .andExpect(model().attributeExists("boards"));
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
}
