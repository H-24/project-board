package org.example.projectboard.repository;

import org.example.projectboard.domain.Boards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "boards")
public interface BoardsRepository extends JpaRepository<Boards, Long> {
}