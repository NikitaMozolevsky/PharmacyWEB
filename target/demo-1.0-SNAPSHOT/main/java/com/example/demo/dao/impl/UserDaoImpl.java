package com.example.demo.dao.impl;

import com.example.demo.dao.BaseDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/mydb"; // TODO: 04.04.2022
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "Root_123");
        /*prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "true");
        prop.put("useJDBCCompliantTimezoneShift", "true");
        prop.put("useLegacyDatetimeCode", "false");
        prop.put("serverTimezone", "UTC");
        prop.put("serverSslCert", "classpath:server.crt");*/
        boolean match = false;
        try (Connection connection = DriverManager.getConnection(url, prop);
             Statement statement = connection.createStatement()) {
            @Language("SQL")
            String sql = "SELECT password FROM users WHERE login = " + login;
            ResultSet resultSet = statement.executeQuery(sql);
            String passFromDB;

            if (resultSet.next()) {
                passFromDB = resultSet.getString(1);
                match = password.equals(passFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return match;
    }
}
