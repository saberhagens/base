package com.example.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource({"classpath:application.yml"})
public class EmailDAOImpl implements EmailDAO {

    @Value("${base.query.get_query}")
    private String get_query;
    @Value("${base.query.post_query}")
    private String post_query;
    @Value("${base.query.delete_query}")
    private String delete_query;

    public void set_get_query(String get_query) {
        this.get_query = get_query;
    }
    public void set_post_query(String post_query) {
        this.post_query = post_query;
    }
    public void set_delete_query(String delete_query) {
        this.delete_query = delete_query;
    }

    @Autowired
    private DataSource dataSource;

    public EmailDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<String> getEmails() {
        List<String> emailList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(get_query);
            while(rs.next()) {
                emailList.add(rs.getString("email"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return emailList;
    }

    @Override
    public String postEmails(String email) {
        if (getEmails().contains(email)) {
            return "email уже существует";
        } else {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(post_query)) {
                connection.setAutoCommit(true);
                preparedStatement.setString(1, email);
                preparedStatement.executeUpdate();
                return "email добавлен в список рассылки";
            } catch (SQLException e) {
                e.printStackTrace();
                return "ошибка при добавлении email";
            }
        }
    }

    @Override
    public String deleteEmails(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(delete_query)) {
            connection.setAutoCommit(true);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            return "email успешно удалён";
        } catch (SQLException e) {
            e.printStackTrace();
            return "ошибка при удалении email";
        }
    }
}
