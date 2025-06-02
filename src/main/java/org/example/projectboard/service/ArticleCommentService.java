package org.example.projectboard.service;

import lombok.RequiredArgsConstructor;
import org.example.projectboard.dto.ArticleCommentsDto;
import org.example.projectboard.repository.ArticleCommentsRepository;
import org.example.projectboard.repository.BoardsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ArticleCommentService {

    private ArticleCommentsRepository articleCommentsRepository;
    private BoardsRepository boardsRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentsDto> searchArticleComment(Long articleId) {
        return List.of();
    }

    public void saveArticleComment(ArticleCommentsDto dto) {
    }
}
