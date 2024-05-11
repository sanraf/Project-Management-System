package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Entity.UserNotification;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.Service.ProjectUserService;
import com.algoExpert.demo.Repository.Service.UserNotificationService;
import com.algoExpert.demo.Repository.UserNotificationRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.algoExpert.demo.AppUtils.AppConstants.USER_NOT_FOUND;

@Service
public class UserNotificationServiceImpl implements UserNotificationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private UserNotificationRepository notificationRepository;
    @Override
    public void createNotification(User user, String notifMsg) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");


        UserNotification userNotificationCreated = UserNotification.builder()
                .notifMsg(notifMsg)
                .notifTime(simpleDateFormat.format(new Date()))
                .fullName(user.getFullName()).build();

        List<UserNotification> userNotificationList =  user.getUserNotificationList();
        userNotificationList.add(userNotificationCreated);
        user.setUserNotificationList(userNotificationList);

        userRepository.save(user);
    }


    public List<UserNotification> findByLoginUser() {
        Integer userId = projectUserService.loggedInUserId();
        return notificationRepository.getNotificationByUserId(userId);
    }

    @Override
    public void deleteNotification(int id) {
        Integer userId = projectUserService.loggedInUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new InvalidArgument(String.format(USER_NOT_FOUND,userId)));
        UserNotification notification = notificationRepository.findById(id).orElseThrow();
        List<UserNotification> userNotificationList = user.getUserNotificationList();
        userNotificationList.remove(notification);
        notificationRepository.delete(notification);
    }

    @Override
    public boolean isDuplicate(Integer userId, Integer taskId) {
        return notificationRepository.countDuplicate(userId,taskId) > 0;
    }


}
