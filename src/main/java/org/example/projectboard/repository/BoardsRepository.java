package org.example.projectboard.repository;

import org.example.projectboard.domain.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Boards, Long> {
}