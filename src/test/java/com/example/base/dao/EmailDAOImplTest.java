package com.example.base.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

class EmailDAOImplTest {

    @MockBean
    private DataSource dataSource;

    @Autowired
    private DataSource dataSource1 = mock(DataSource.class);

    @Test
    void getEmails() {
    }

    @Test
    void postEmails() {
    }

    @Test
    void deleteEmails() {
    }
}