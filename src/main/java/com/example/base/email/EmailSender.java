package com.example.base.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSender {
    JavaMailSender emailSender;

    @Value("${spring.mail.message}")
    private String message;
    @Value("${spring.mail.theme}")
    private String theme;

    public EmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String toAddress) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(theme);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }
}
