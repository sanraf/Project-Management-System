package com.algoExpert.demo.AppNotification;

import org.springframework.stereotype.Component;

@Component
public interface AppEmailBuilder {

    void sendEmailInvite(String to,String email);
    void sendTaskReminderEmail(String to,String body,String dueDay);
    void sendEmailAccountConfirmation(String to,String email);
}
