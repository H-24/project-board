package org.example.projectboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectboard.domain.constant.FormStatus;
import org.example.projectboard.domain.constant.SearchType;
import org.example.projectboard.dto.UserAccountDto;
import org.example.projectboard.dto.request.BoardsRequest;
import org.example.projectboard.response.ArticleWithCommentsResponse;
import org.example.projectboard.response.BoardsResponse;
import org.example.projectboard.service.BoardService;
import org.example.projectboard.service.PaginationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@Controller
public class BoardsController {

    private final BoardService boardService;
    private final PaginationService paginationService;

    @GetMapping
    public String boards(
        @RequestParam(name ="searchType", required = false) SearchType searchType,
        @RequestParam(name = "searchValue", required = false) String searchValue,
        @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
        ModelMap map
    ) {
        Page<BoardsResponse> boards = boardService.searchBoards(searchType, searchValue, pageable).map(BoardsResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), boards.getTotalPages());

        map.addAttribute("boards", boards);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchType", SearchType.values());

        return "boards/index";
    }

    @GetMapping("/{articleId}")
    public String boards(@PathVariable("articleId") Long articleId, ModelMap map) {
        ArticleWithCommentsResponse board = ArticleWithCommentsResponse.from(boardService.getArticleWithComments(articleId));
        map.addAttribute("boards", board);
        map.addAttribute("articleComments", board.articleCommentsResponse());
        map.addAttribute("totalCount", boardService.getBoardsCount());

        return "boards/details";
    }

    @GetMapping("/form")
    public String articleForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);

        return "boards/form";
    }

    @PostMapping("/form")
    public String postNewArticle(BoardsRequest boardsRequest) {
        // TODO: 인증 정보를 넣어줘야 한다.
        boardService.saveBoards(boardsRequest.toDto(UserAccountDto.of(
                1L, "win", "asdf1234", "uno@mail.com", "Uno", "memo", null, null, null, null
        )));

        return "redirect:/boards";
    }

    @GetMapping("/{articleId}/form")
    public String updateArticleForm(@PathVariable("articleId") Long articleId, ModelMap map) {
        BoardsResponse boards = BoardsResponse.from(boardService.getBoards(articleId));

        map.addAttribute("boards", boards);
        map.addAttribute("formStatus", FormStatus.UPDATE);

        return "boards/form";
    }

    @PostMapping ("/{articleId}/form")
    public String updateArticle(@PathVariable("articleId") Long articleId, BoardsRequest boardsRequest) {
        // TODO: 인증 정보를 넣어줘야 한다.
        boardService.updateBoards(articleId, boardsRequest.toDto(UserAccountDto.of(
                1L, "uno", "asdf1234", "win@mail.com", "Win", "memo", null, null, null, null
        )));

        return "redirect:/boards/" + articleId;
    }

    @PostMapping ("/{articleId}/delete")
    public String deleteArticle(@PathVariable("articleId") Long articleId) {
        // TODO: 인증 정보를 넣어줘야 한다.
        boardService.deleteBoards(articleId);

        return "redirect:/boards";
    }
}
