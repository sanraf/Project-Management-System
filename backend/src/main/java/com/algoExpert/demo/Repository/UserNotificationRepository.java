package com.algoExpert.demo.Repository;

import com.algoExpert.demo.Entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification,Integer> {
    @Query(value = "SELECT * FROM user_notification WHERE user_id=:userId",nativeQuery = true)
    List<UserNotification> getAllNotification(Integer userId);
}
