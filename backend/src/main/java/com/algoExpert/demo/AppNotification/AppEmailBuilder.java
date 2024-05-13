package com.algoExpert.demo.AppNotification;

import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public interface AppEmailBuilder {
    @Async
    void sendEmailInvite(String to,String email,String subject);
    @Async
    void sendTaskReminderEmail(String to,String body,String dueDay);
    @Async
    void sendEmailAccountConfirmation(String to,String email)throws MessagingException, IOException;
    @Async
    void sendEmailResetPassword(String to, String htmlBody);

}
