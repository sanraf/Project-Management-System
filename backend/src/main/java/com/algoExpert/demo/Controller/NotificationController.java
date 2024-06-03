package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.NotificationSettings;
import com.algoExpert.demo.Entity.UserNotification;
import com.algoExpert.demo.Repository.Service.Impl.UserNotificationServiceImpl;
import com.algoExpert.demo.Repository.Service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private UserNotificationService notificationService;

    @DeleteMapping("/delete/{Id}")
    private String deleteNotification(@PathVariable Integer Id){
        notificationService.deleteNotification(Id);
        return "deleted";
    }
    @GetMapping("/getAll")
    private List<UserNotification> getByUserId(){
        return notificationService.findByLoginUser();
    }

    //    @PostMapping("/create")
//    private UserNotification createNotification(){
//        return userNotificationService.createNotification("user1@gmail.com","hello");
//
//    }
    @PostMapping("/sendNotifiSettings")
    private String setNotificationSettings(@RequestBody NotificationSettings notSettings ){
        return notificationService.updateNotiSettings(notSettings);
    }
    @GetMapping("/getNotifSettings")
    private NotificationSettings findNotifSettings(){
        return notificationService.findNotificationSettings();
    }


}
