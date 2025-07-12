package org.example.projectboard.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.projectboard.domain.ArticleComments;
import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.UserAccount;
import org.example.projectboard.dto.ArticleCommentsDto;
import org.example.projectboard.repository.ArticleCommentsRepository;
import org.example.projectboard.repository.BoardsRepository;
import org.example.projectboard.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ArticleCommentService {

    private final UserAccountRepository userAccountRepository;
    private final ArticleCommentsRepository articleCommentsRepository;
    private final BoardsRepository boardsRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentsDto> searchArticleComments(Long articleId) {

        return articleCommentsRepository.findByBoards_Id(articleId)
                .stream()
                .map(ArticleCommentsDto::from)
                .toList();
    }

    public void saveArticleComment(ArticleCommentsDto dto) {
        try {
            Boards boards = boardsRepository.getReferenceById(dto.articleId());
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().id());
            articleCommentsRepository.save(dto.toEntity(boards, userAccount));
        } catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패. 댓글의 게시글을 찾을 수 없습니다. - dto: {}", dto);
        }
    }

    public void updateArticleComment(ArticleCommentsDto dto) {
        try {
            ArticleComments articleComments = articleCommentsRepository.getReferenceById(dto.id());
            if (dto.content() != null) { articleComments.setContent(dto.content()); }
        } catch (EntityNotFoundException e) {
            log.warn("댓글 업데이트 실패. 댓글을 찾을 수 없습니다. - dto: {}", dto);
        }
    }

    public void deleteArticleComment(Long articleCommentId, String userId) {
        articleCommentsRepository.deleteByIdAndUserAccount_UserId(articleCommentId, userId);
    }
}
