package org.example.projectboard.repository;

import org.example.projectboard.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUserId(String userId);

    UserAccount getReferenceByUserId(String userId);
}
