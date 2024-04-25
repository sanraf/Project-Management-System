package com.algoExpert.demo.UserAccount.AccountInfo.Repository;

import com.algoExpert.demo.UserAccount.AccountInfo.Entity.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset,Integer> {

    @Query(value = "SELECT * FROM password_reset WHERE user_id=:userId",nativeQuery = true)
    PasswordReset findPasswordByUserId(int userId);

    Optional<PasswordReset> findByPasswordToken(String token);

}
