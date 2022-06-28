package com.example.base.controller;

import com.example.base.dao.EmailDAO;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailChangerControllerImpl implements EmailChangerController{

    private EmailDAO emailChanger;

    public EmailChangerControllerImpl(EmailDAO emailChanger){
        this.emailChanger = emailChanger;
    }

    @Override
    @GetMapping
    public List<String> getEmails() {
        return emailChanger.getEmails();
    }

    @Override
    @PostMapping
    public String postEmails(@NotNull @RequestBody String email) {
        return emailChanger.postEmails(email);
    }

    @Override
    @DeleteMapping
    public String deleteEmails(@NotNull @RequestBody String email) {
        return emailChanger.deleteEmails(email);
    }

}