package com.algoExpert.demo.Repository;

import com.algoExpert.demo.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByToken(String token);
    @Query(value = "SELECT * FROM refresh_token WHERE user_id = :userId", nativeQuery = true)
    Optional<RefreshToken> findTokenByUserId(int userId);

}
