package org.example.projectboard.repository;

import org.example.projectboard.domain.ArticleComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "article-comments")
public interface ArticleCommentsRepository extends JpaRepository<ArticleComments, Long> {
}
