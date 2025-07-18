package org.example.projectboard.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.example.projectboard.domain.Boards;
import org.example.projectboard.domain.QBoards;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "boards")
public interface BoardsRepository extends
        JpaRepository<Boards, Long>,
        QuerydslPredicateExecutor<Boards>,
        QuerydslBinderCustomizer<QBoards> {

    Page<Boards> findByTitleContaining(String title, Pageable pageable);
    Page<Boards> findByContentContaining(String content, Pageable pageable);
    Page<Boards> findByUserAccount_UserId(String userId, Pageable pageable);
    Page<Boards> findByUserAccount_Nickname(String nickname, Pageable pageable);
    Page<Boards> findByHashtag(String hashtag, Pageable pageable);

    void deleteByIdAndUserAccount_UserId(Long id, String userId);

    @Override
    default void customize(QuerydslBindings bindings, QBoards root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
