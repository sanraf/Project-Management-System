package com.algoExpert.demo.Repository;

import com.algoExpert.demo.Entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification,Integer> {
    @Query(value = "SELECT * FROM user_notification WHERE user_id=:userId",nativeQuery = true)
    List<UserNotification> getNotificationByUserId(Integer userId);

    @Query(value = "SELECT * FROM user_notification WHERE user_id=:taskId",nativeQuery = true)
    List<UserNotification> getNotificationByTaskId(Integer taskId);

    @Query(value = "SELECT COUNT(*) FROM user_notification WHERE user_id=:userId AND task_id=:taskId",nativeQuery = true)
    Integer countDuplicate(Integer userId,Integer taskId);
}
