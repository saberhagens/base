package com.example.base.scheduler;

import com.example.base.dao.EmailDAO;
import com.example.base.email.EmailSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class SchedulerConfig {

    private EmailSender emailSender;
    private EmailDAO emailChanger;

    public SchedulerConfig(EmailSender emailSender, EmailDAO emailChanger) {
        this.emailSender = emailSender;
        this.emailChanger = emailChanger;
    }

    @Scheduled(cron = "${base.scheduler.cron}")
    @Async
    public void scheduledEmailSending() {
        for (String s : emailChanger.getEmails()) {
            emailSender.sendEmail(s);
        }
    }
}
