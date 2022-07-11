package com.example.base.controller;

import com.example.base.dao.EmailDAOImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmailChangerControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailDAOImpl emailChanger;


    @Test
    public void getEmailsTest() throws Exception {
        Mockito.when(emailChanger.getEmails()).thenReturn(Collections.singletonList("dog-pog@ya.ru"));

        mockMvc.perform(
                get("/emails"))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"dog-pog@ya.ru\"]"));
    }

    @Test
    public void postEmailsTest() throws Exception {
        Mockito.when(emailChanger.postEmails("dog-pog@ya.ru")).thenReturn("email добавлен в список рассылки");

        mockMvc.perform(
                post("/emails")
                        .content("dog-pog@ya.ru")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("email добавлен в список рассылки"));

    }

    @Test
    public void deleteEmailsTest() throws Exception {
        Mockito.when(emailChanger.deleteEmails("dog-pog@ya.ru")).thenReturn("email успешно удалён");

        mockMvc.perform(
                delete("/emails")
                        .content("dog-pog@ya.ru")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("email успешно удалён"));
    }
}