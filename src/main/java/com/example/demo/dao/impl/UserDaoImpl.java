package com.example.demo.dao.impl;

import com.example.demo.dao.BaseDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.mapper.impl.UserMapper;
import com.example.demo.entity.user.AccessLevel;
import com.example.demo.entity.user.User;
import com.example.demo.exception.DaoException;
import com.example.demo.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.example.demo.command.constant.OrderAttribute.ORDER_ID;
import static com.example.demo.command.constant.UserAttribute.*;
import static com.example.demo.dao.request.UserDaoRequest.*;

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
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS);
             PreparedStatement statementAccessLevel = connection.prepareStatement(GET_ALL_USERS_ACCESS_LEVELS);
             ResultSet resultSet = statement.executeQuery();
             ResultSet resultSetAccessLevel = statementAccessLevel.executeQuery()
             ) {
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt(USER_ID));
                user.setUserName(resultSet.getString(USER_NAME));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPhone(resultSet.getString(PHONE));
                user.setMoneyAmount(resultSet.getDouble(MONEY_AMOUNT));
                users.add(user);
            }
            int i = 0;
            while (resultSetAccessLevel.next()) {
                User user = users.get(i);
                user.setAccessLevel(AccessLevel.valueOf(resultSetAccessLevel.getString(ACCESS_LEVEL)));
                i++;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in finding all products", e);
        }
        return users;
    }

    public ArrayDeque<String> findUserAccessLevel() {
        ArrayDeque<String> accessLevels = new ArrayDeque<>();
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Optional<User> authenticateDao(String login, String password) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement authenticateStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            authenticateStatement.setString(1, login);
            authenticateStatement.setString(2, password);
            try (ResultSet resultSet = authenticateStatement.executeQuery()) { // TODO: 18.04.2022 implement try-with-resources for resultSet
                /*String passFromDB;
                passFromDB = resultSet.getString(PASSWORD);*/
                if (resultSet.next()) {
                    optionalUser = UserMapper.getInstance().mapEntity(resultSet);
                    logger.log(Level.INFO, "user was found by login {} and hash password {}", login, password);
                    /*match = password.equals(passFromDB);*/
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "authenticate error", e);
            throw new DaoException("authenticate error", e);
        }
        return optionalUser;
    }

    public boolean registerDao(User user, String hashPassword) throws DaoException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(REGISTER_USER);
             PreparedStatement getUserIdStatement = connection.prepareStatement(GET_USER_ID);
             PreparedStatement accessLevelStatement = connection.prepareStatement(ADD_CLIENT_ACCESS_LEVEL)) {

            String userId;
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getLogin());
            statement.setString(3, hashPassword);
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            /*statement.setString(6, String.valueOf(user.getAccessLevel()));*/
            statement.setDouble(6, user.getMoneyAmount());
            statement.execute();
            getUserIdStatement.setString(1, user.getLogin());
            try (ResultSet resultSet = getUserIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getString(USER_ID);
                    accessLevelStatement.setString(1, userId);
                    accessLevelStatement.setString(2, String.valueOf(user.getAccessLevel()));
                    accessLevelStatement.execute();
                }
            }
            logger.log(Level.INFO, "user was registered successful");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "user was not registered", e);
            throw new DaoException();
        }
        return true;
    }
}
