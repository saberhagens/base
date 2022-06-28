package com.example.base.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface EmailChangerController {

    @GetMapping("/emails")
    List<String> getEmails();

    @PostMapping("/emails")
    String postEmails(String email);

    @DeleteMapping("/emails")
    String deleteEmails(String email);
}
