package org.example.projectboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectboard.dto.request.ArticleCommentRequest;
import org.example.projectboard.dto.security.BoardPrincipal;
import org.example.projectboard.service.ArticleCommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comments")
public class ArticleCommentsController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            ArticleCommentRequest articleCommentRequest)
    {
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/boards/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(
            @PathVariable("commentId") Long commentId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            @RequestParam("articleId") Long articleId)
    {
        articleCommentService.deleteArticleComment(commentId, boardPrincipal.getUsername());

        return "redirect:/boards/" + articleId;
    }
}
