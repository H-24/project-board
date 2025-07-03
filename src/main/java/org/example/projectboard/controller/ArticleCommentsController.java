package org.example.projectboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectboard.dto.UserAccountDto;
import org.example.projectboard.dto.request.ArticleCommentRequest;
import org.example.projectboard.service.ArticleCommentService;
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
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
        //TODO: 인증 정보를 넣어줘야 한다.
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                1L, "win", "pyj0101", "win@mail.com", null, null, null, null, null, null
        )));

        return "redirect:/boards/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable("commentId") Long commentId, @RequestParam("articleId") Long articleId) {

        articleCommentService.deleteArticleComment(commentId);

        return "redirect:/boards/" + articleId;
    }
}
