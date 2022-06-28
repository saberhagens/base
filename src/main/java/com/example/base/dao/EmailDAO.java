package com.example.base.dao;

import java.util.List;

public interface EmailDAO {
    List<String> getEmails();
    String postEmails(String s);
    String deleteEmails(String s);
}