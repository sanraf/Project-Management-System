package com.algoExpert.demo.AppNotification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
;
import java.nio.charset.StandardCharsets;


@Service
@Slf4j
@RequiredArgsConstructor
public class AppEmailService implements AppEmailBuilder {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmailInvite(String to, String htmlBody,String subject) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,StandardCharsets.UTF_8.name());
            messageHelper.setText(htmlBody,true);
            messageHelper.setReplyTo("no-reply@pmsteam.com");
            messageHelper.setTo(to);
            messageHelper.setPriority(1);
            messageHelper.setSubject(subject);
            messageHelper.setFrom("no-reply@example.com");
            javaMailSender.send(mimeMessage);
        }catch (MailSendException e) {
            log.error("Failed to send email: {}", e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTaskReminderEmail(String to, String htmlBody,String dueDay) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,StandardCharsets.UTF_8.name());
            messageHelper.setText(htmlBody,true);
            messageHelper.setReplyTo("no-reply@pmsteam.com");
            messageHelper.setTo(to);
            messageHelper.setPriority(1);
            messageHelper.setSubject("Task due date reminder "+dueDay);
            javaMailSender.send(mimeMessage);
        }catch (MailSendException e) {
            log.error("Failed to send email: {}", e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailAccountConfirmation(String to, String htmlBody) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true, StandardCharsets.UTF_8.name());
            messageHelper.setText(htmlBody,true);
            messageHelper.setReplyTo("no-reply@pmsteam.com");
            messageHelper.setTo(to);
            messageHelper.setPriority(1);
            messageHelper.setSubject("Your account has been created");
            javaMailSender.send(mimeMessage);
        }catch (MailSendException e) {
            log.error("Failed to send email: {}", e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailResetPassword(String to, String link) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true, StandardCharsets.UTF_8.name());
            messageHelper.setText("Click the link below to reset your password\n "+link);
            messageHelper.setReplyTo("no-reply@pmsteam.com");
            messageHelper.setTo(to);
            messageHelper.setPriority(1);
            messageHelper.setSubject("Password Reset");
            javaMailSender.send(mimeMessage);
        }catch (MailSendException e) {
            log.error("Failed to send email: {}", e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
