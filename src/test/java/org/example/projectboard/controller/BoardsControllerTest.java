package org.example.projectboard.controller;

import org.example.projectboard.config.SecurityConfig;
import org.example.projectboard.domain.constant.FormStatus;
import org.example.projectboard.domain.constant.SearchType;
import org.example.projectboard.dto.ArticleWithCommentsDto;
import org.example.projectboard.dto.BoardsDto;
import org.example.projectboard.dto.UserAccountDto;
import org.example.projectboard.dto.request.BoardsRequest;
import org.example.projectboard.response.BoardsResponse;
import org.example.projectboard.service.BoardService;
import org.example.projectboard.service.PaginationService;
import org.example.projectboard.util.FormDataEncoder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import({SecurityConfig.class, FormDataEncoder.class})
@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(BoardsController.class) // 슬라이스 테스트(특정 계층만 테스트-여기서는 controller)
class BoardsControllerTest {

    private final MockMvc mvc;
    private final FormDataEncoder formDataEncoder;

    @MockBean private BoardService boardService;
    @MockBean private PaginationService paginationService;

    public BoardsControllerTest(@Autowired MockMvc mvc, @Autowired FormDataEncoder formDataEncoder) {

        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

    @DisplayName("[view] [GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingBoardsView_thenReturnsBoardsView() throws Exception {
        // Given
        given(boardService.searchBoards(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));

        // When & Then
        mvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/index"))
                .andExpect(model().attributeExists("boards"))
                .andExpect(model().attributeExists("paginationBarNumbers"))
                .andExpect(model().attributeExists("searchType"));
        then(boardService).should().searchBoards(eq(null), eq(null), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 검색어와 함께 호출")
    @Test
    public void givenSearchKeyword_whenSearchingArticlesView_thenReturnsArticlesView() throws Exception {
        // Given
        SearchType searchType = SearchType.TITLE;
        String searchValue = "title";
        given(boardService.searchBoards(eq(searchType), eq(searchValue), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));

        // When & Then
        mvc.perform(
                        get("/boards")
                                .queryParam("searchType", searchType.name())
                                .queryParam("searchValue", searchValue)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/index"))
                .andExpect(model().attributeExists("boards"))
                .andExpect(model().attributeExists("searchType"));
        then(boardService).should().searchBoards(eq(searchType), eq(searchValue), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 페이징, 정렬 기능")
    @Test
    void givenPagingAndSortingParams_whenSearchingArticlesPage_thenReturnsArticlesPage() throws Exception {
        // Given
        String sortName = "title";
        String direction = "desc";
        int pageNumber = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc(sortName)));
        List<Integer> barNumbers = List.of(1, 2, 3, 4, 5);
        given(boardService.searchBoards(null, null, pageable)).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(pageable.getPageNumber(), Page.empty().getTotalPages())).willReturn(barNumbers);

        // When & Then
        mvc.perform(
                        get("/boards")
                                .queryParam("page", String.valueOf(pageNumber))
                                .queryParam("size", String.valueOf(pageSize))
                                .queryParam("sort", sortName + "," + direction)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/index"))
                .andExpect(model().attributeExists("boards"))
                .andExpect(model().attribute("paginationBarNumbers", barNumbers));
        then(boardService).should().searchBoards(null, null, pageable);
        then(paginationService).should().getPaginationBarNumbers(pageable.getPageNumber(), Page.empty().getTotalPages());
    }

    @DisplayName("[view] [GET] 게시글 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingBoardView_thenReturnsBoardView() throws Exception {
        // Given
        Long boardId = 1L;
        long totalCount = 1L;

        given(boardService.getArticleWithComments(boardId)).willReturn(createArticleWithCommentsDto());
        given(boardService.getBoardsCount()).willReturn(totalCount);

        // When & Then
        mvc.perform(get("/boards/" + boardId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/details"))
                .andExpect(model().attributeExists("boards"))
                .andExpect(model().attributeExists("articleComments"))
                .andExpect(model().attribute("totalCount", totalCount));
        then(boardService).should().getArticleWithComments(boardId);
        then(boardService).should().getBoardsCount();
    }

    @Disabled("보류")
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

    @Disabled("보류")
    @DisplayName("[view] [GET] 게시글 리스트 해시태그 검색 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingBoardsHashtagSearchView_thenReturnsBoardsHashtagSearchView() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/boards/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/search-hashtag"));
    }

    @DisplayName("[view][GET] 새 게시글 작성 페이지")
    @Test
    void givenNothing_whenRequesting_thenReturnsNewArticlePage() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/boards/form"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/form"))
                .andExpect(model().attribute("formStatus", FormStatus.CREATE));
    }

    @DisplayName("[view][POST] 새 게시글 등록 - 정상 호출")
    @Test
    void givenNewArticleInfo_whenRequesting_thenSavesNewArticle() throws Exception {
        // Given
        BoardsRequest boardsRequest = BoardsRequest.of("new title", "new content", "#new");
        willDoNothing().given(boardService).saveBoards(any(BoardsDto.class));

        // When & Then
        mvc.perform(
                        post("/boards/form")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .content(formDataEncoder.encode(boardsRequest))
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/boards"))
                .andExpect(redirectedUrl("/boards"));
        then(boardService).should().saveBoards(any(BoardsDto.class));
    }

    @DisplayName("[view][GET] 게시글 수정 페이지")
    @Test
    void givenNothing_whenRequesting_thenReturnsUpdatedArticlePage() throws Exception {
        // Given
        long articleId = 1L;
        BoardsDto dto = createBoardsDto();
        given(boardService.getBoards(articleId)).willReturn(dto);

        // When & Then
        mvc.perform(get("/boards/" + articleId + "/form"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("boards/form"))
                .andExpect(model().attribute("boards", BoardsResponse.from(dto)))
                .andExpect(model().attribute("formStatus", FormStatus.UPDATE));
        then(boardService).should().getBoards(articleId);
    }

    @DisplayName("[view][POST] 게시글 수정 - 정상 호출")
    @Test
    void givenUpdatedArticleInfo_whenRequesting_thenUpdatesNewArticle() throws Exception {
        // Given
        long articleId = 1L;
        BoardsRequest boardsRequest = BoardsRequest.of("new title", "new content", "#new");
        willDoNothing().given(boardService).updateBoards(eq(articleId), any(BoardsDto.class));

        // When & Then
        mvc.perform(
                        post("/boards/" + articleId + "/form")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .content(formDataEncoder.encode(boardsRequest))
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/boards/" + articleId))
                .andExpect(redirectedUrl("/boards/" + articleId));
        then(boardService).should().updateBoards(eq(articleId), any(BoardsDto.class));
    }

    @DisplayName("[view][POST] 게시글 삭제 - 정상 호출")
    @Test
    void givenArticleIdToDelete_whenRequesting_thenDeletesArticle() throws Exception {
        // Given
        long articleId = 1L;
        willDoNothing().given(boardService).deleteBoards(articleId);

        // When & Then
        mvc.perform(
                        post("/boards/" + articleId + "/delete")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/boards"))
                .andExpect(redirectedUrl("/boards"));
        then(boardService).should().deleteBoards(articleId);
    }


    private BoardsDto createBoardsDto() {
        return BoardsDto.of(
                createUserAccountDto(),
                "title",
                "content",
                "#java"
        );
    }
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
