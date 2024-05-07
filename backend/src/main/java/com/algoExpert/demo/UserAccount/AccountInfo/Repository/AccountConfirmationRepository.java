package com.algoExpert.demo.UserAccount.AccountInfo.Repository;

import com.algoExpert.demo.UserAccount.AccountInfo.Entity.AccountConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountConfirmationRepository extends JpaRepository<AccountConfirmation,Integer> {

    @Query(value = "SELECT * FROM account_confirmation WHERE user_id=:userId",nativeQuery = true)
    AccountConfirmation findByUserId(int userId);
    Optional<AccountConfirmation> findByToken(String token);

}
