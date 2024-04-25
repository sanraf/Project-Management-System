package com.algoExpert.demo.AppNotification;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public interface AppEmailBuilder {
    @Async
    void sendEmailInvite(String to,String email);
    @Async
    void sendTaskReminderEmail(String to,String body,String dueDay);
    @Async
    void sendEmailAccountConfirmation(String to,String email);
}
