package org.example.projectboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectboard.domain.type.SearchType;
import org.example.projectboard.response.ArticleWithCommentsResponse;
import org.example.projectboard.response.BoardsResponse;
import org.example.projectboard.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/boards")
@Controller
public class BoardsController {

    private final BoardService boardService;

    @GetMapping
    public String boards(
        @RequestParam(name ="searchType", required = false) SearchType searchType,
        @RequestParam(name = "searchValue", required = false) String searchValue,
        @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
        ModelMap model
    ) {
        model.addAttribute("boards", boardService.searchBoards(searchType, searchValue, pageable).map(BoardsResponse::from));

        return "boards/index";
    }

    @GetMapping("/{articleId}")
    public String boards(@PathVariable("articleId") Long articleId, ModelMap model) {
        ArticleWithCommentsResponse board = ArticleWithCommentsResponse.from(boardService.getBoard(articleId));
        model.addAttribute("boards", board);
        model.addAttribute("articleComments", board.articleCommentsResponse());

        return "boards/details";
    }
}
