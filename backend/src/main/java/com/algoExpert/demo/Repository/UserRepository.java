package com.algoExpert.demo.Repository;

import com.algoExpert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);
    @Query("SELECT u FROM User u WHERE u.fullname LIKE %:fullnameLetters%")
    List<User> findByFullname(@Param("fullnameLetters") String fullnameLetters);

    @Query("SELECT MONTH(u.dateRegistered), COUNT(u) FROM User u WHERE YEAR(u.dateRegistered) = :year GROUP BY MONTH(u.dateRegistered)")
    List<Object[]> getNewUsers(int year);


}
