package com.algoExpert.demo.AppNotification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppEmailService implements AppEmailBuilder {

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmailInvite(String to, String message) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            messageHelper.setText(message,true);
            messageHelper.setTo(to);
            messageHelper.setPriority(1);
            messageHelper.setSubject("You've been invited to the project");
            messageHelper.setFrom("santo");
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            log.error("failed to send message ", e);
            throw new IllegalStateException("failed to send message");
        }
    }

    @Override
    @Async
    public void sendTaskReminderEmail(String to, String body,String dueDay) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            messageHelper.setText(body,true);
            messageHelper.setTo(to);
            messageHelper.setPriority(1);
            messageHelper.setSubject("Task due date reminder "+dueDay);
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            log.error("failed to send message ", e);
            throw new IllegalStateException("failed to send message");
        }
    }

    @Override
    @Async
    public void sendEmailAccountConfirmation(String to, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            messageHelper.setText(body,true);
            messageHelper.setTo(to);
            messageHelper.setPriority(1);
            messageHelper.setSubject("Your account has been create");
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            log.error("failed to send message ", e);
            throw new IllegalStateException("failed to send message");
        }
    }
}
