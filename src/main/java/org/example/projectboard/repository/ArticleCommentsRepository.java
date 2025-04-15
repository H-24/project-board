package org.example.projectboard.repository;

import org.example.projectboard.domain.ArticleComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentsRepository extends JpaRepository<ArticleComments, Long> {
}
