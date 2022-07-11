package com.example.base.dao;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmailDAOImplTest {

    public static DataSource h2DataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testdb");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("sa");
        return dataSourceBuilder.build();
    }
    private static EmailDAOImpl emailChanger = new EmailDAOImpl(h2DataSource());

    @BeforeAll
    public static void setup() throws SQLException {
        emailChanger.set_get_query("select * from emails");
        emailChanger.set_post_query("INSERT INTO emails (email) VALUES (?)");
        emailChanger.set_delete_query("DELETE FROM emails where email=?");
        Statement statement = h2DataSource().getConnection().createStatement();
        statement.executeUpdate("create table emails(emailid number(10) IDENTITY NOT NULL PRIMARY KEY , email varchar(255) not null)");
    }

    @AfterEach
    public void clean() throws SQLException {
        Statement statement = h2DataSource().getConnection().createStatement();
        statement.executeUpdate("DELETE FROM emails");
    }


    @Test
    public void getEmails_emptyResponse() throws SQLException {
        List<String> emailList = new ArrayList<>();
        Assertions.assertEquals(emailList, emailChanger.getEmails());
    }

    @Test
    public void getEmails_one_entry() throws SQLException {
        List<String> emailList = new ArrayList<>();
        emailList.add("dog-pog@ya.ru");
        Statement statement = h2DataSource().getConnection().createStatement();
        statement.executeUpdate("insert into emails (email) values ('dog-pog@ya.ru')");
        Assertions.assertEquals(emailList, emailChanger.getEmails());
    }

    @Test
    public void getEmails_many_entries() throws SQLException {
        List<String> emailList = new ArrayList<>();
        emailList.add("dog-pog@ya.ru");
        emailList.add("dog-pog1@ya.ru");
        Statement statement = h2DataSource().getConnection().createStatement();
        statement.executeUpdate("insert into emails (email) values ('dog-pog@ya.ru')");
        statement.executeUpdate("insert into emails (email) values ('dog-pog1@ya.ru')");
        Assertions.assertEquals(emailList, emailChanger.getEmails());
    }

    @Test
    public void postEmails_email_already_exists() throws SQLException {
        Statement statement = h2DataSource().getConnection().createStatement();
        statement.executeUpdate("insert into emails (email) values ('dog-pog@ya.ru')");
        Assertions.assertEquals("email уже существует", emailChanger.postEmails("dog-pog@ya.ru"));
    }

    @Test
    public void postEmails_email_added() {
        Assertions.assertEquals("email добавлен в список рассылки", emailChanger.postEmails("dog-pog@ya.ru"));
    }

    @Test
    public void deleteEmails() {
        Assertions.assertEquals("email успешно удалён", emailChanger.deleteEmails("dog-pog@ya.ru"));
    }
}