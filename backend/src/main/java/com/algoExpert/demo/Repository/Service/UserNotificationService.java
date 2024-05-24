package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Entity.UserNotification;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserNotificationService {

     void createNotification(User user, String notifMsg);
     @Transactional
     void deleteNotification(int id);
     boolean isDuplicate(Integer userId,Integer taskId);
     List<UserNotification> userNotifications();

}
