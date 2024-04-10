package com.nte.financecore.repository;

import com.nte.financecore.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUsername(String username);
    void deleteAllByUsername(String username);

    Boolean existsByUsername(String username);
}
