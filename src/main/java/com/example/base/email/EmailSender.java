package com.example.base.email;

import com.example.base.dao.EmailDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSender {
    private JavaMailSender emailSender;
    private EmailDAO emailChanger;

    @Value("${spring.mail.message}")
    private String message;
    @Value("${spring.mail.theme}")
    private String theme;

    public EmailSender(JavaMailSender emailSender, EmailDAO emailChanger) {
        this.emailSender = emailSender;
        this.emailChanger = emailChanger;
    }

    public void sendEmail(String toAddress) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(theme);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }
}
