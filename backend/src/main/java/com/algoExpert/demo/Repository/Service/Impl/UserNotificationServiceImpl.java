package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Entity.UserNotification;
import com.algoExpert.demo.Repository.Service.UserNotificationService;
import com.algoExpert.demo.Repository.UserNotificationRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class UserNotificationServiceImpl implements UserNotificationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectUserImpl ProjectUserImpl;
    @Autowired
    private UserNotificationRepository notificationRepository;
    @Override
    public void createNotification(User user, String notifMsg,int taskId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");

        UserNotification userNotification = UserNotification.builder()
                .notifMsg(notifMsg)
                .notifTime(simpleDateFormat.format(new Date()))
                .fullName(user.getFullName())
                .taskId(taskId)
                .user(user).build();
        notificationRepository.save(userNotification);
    }
//

    public List<UserNotification> findByLoginUser() {
        return notificationRepository.getNotificationByUserId(ProjectUserImpl.loggedInUserId());
    }

    @Override
    public void deleteNotification(int id) {
        UserNotification notification = notificationRepository.findById(id).orElseThrow();
        notificationRepository.delete(notification);
    }

    @Override
    public boolean isDuplicate(Integer userId, Integer taskId) {
        return notificationRepository.countDuplicate(userId,taskId) > 0;
    }


}
