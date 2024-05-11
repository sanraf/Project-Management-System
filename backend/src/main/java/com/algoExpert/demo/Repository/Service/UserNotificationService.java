package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public interface UserNotificationService {

     void createNotification(User user, String notifMsg);
     @Transactional
     void deleteNotification(int id);
     boolean isDuplicate(Integer userId,Integer taskId);

}
