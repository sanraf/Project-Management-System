package com.algoExpert.demo.AppNotification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class EmailService implements EmailProjectInvite{

    private final JavaMailSender javaMailSender;
    @Override
    @Async
    public void sendEmailInvite(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            messageHelper.setText(email,true);
            messageHelper.setTo(to);
            messageHelper.setSubject("You've been invited to the project");
            messageHelper.setFrom("santo");
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            log.error("failed to send email ", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
