package com.example.demo.dao.impl;

import com.example.demo.dao.BaseDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.exception.DaoException;
import com.example.demo.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.demo.command.constant.UserAttribute.PASSWORD;
import static com.example.demo.dao.DaoRequest.REGISTER_USER;
import static com.example.demo.dao.DaoRequest.SELECT_LOGIN_PASSWORD;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static UserDaoImpl instance = new UserDaoImpl();
    static Logger logger = LogManager.getLogger();

    public UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException("delete is unsupported operation");
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
    public boolean authenticateDao(String login, String password) throws DaoException {
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) { // TODO: 18.04.2022 implement try-with-resources for resultSet
                String passFromDB;
                if (resultSet.next()) {
                    passFromDB = resultSet.getString(PASSWORD);
                    match = password.equals(passFromDB);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("authenticate error", e);
        }
        return match;
    }

    public boolean registerDao(User user, String hashPassword) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(REGISTER_USER)) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getLogin());
            statement.setString(3, hashPassword);
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            statement.setString(6, String.valueOf(user.getAccessLevel()));
            statement.setDouble(7, user.getMoneyAmount());

            statement.execute();
            logger.log(Level.INFO, "user was registered successful");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "user was not registered");
        }
        return true;
    }
}
