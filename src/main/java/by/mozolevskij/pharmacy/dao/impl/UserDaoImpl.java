package by.mozolevskij.pharmacy.dao.impl;

import by.mozolevskij.pharmacy.command.attribute.UserAttribute;
import by.mozolevskij.pharmacy.dao.mapper.impl.UserMapper;
import by.mozolevskij.pharmacy.dao.request.OrderDaoRequest;
import by.mozolevskij.pharmacy.dao.request.UserDaoRequest;
import by.mozolevskij.pharmacy.entity.user.User;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.pool.ConnectionPool;
import by.mozolevskij.pharmacy.dao.BaseDao;
import by.mozolevskij.pharmacy.dao.UserDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.INITIAL_MONEY_AMOUNT;
import static by.mozolevskij.pharmacy.dao.request.OrderDaoRequest.*;
import static by.mozolevskij.pharmacy.dao.request.UserDaoRequest.*;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static UserDaoImpl instance = new UserDaoImpl();
    static Logger logger = LogManager.getLogger();

    public UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        throw new UnsupportedOperationException
                ("delete is unsupported operation");
    }

    @Override
    public List<Optional<User>> findAll() throws DaoException {
        Optional<User> optionalUser;
        List<Optional<User>> users = new ArrayList<>();
        UserMapper userMapper = UserMapper.getInstance();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (UserDaoRequest.GET_ALL_USERS_WITH_ACCESS_LEVEL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                optionalUser = userMapper.mapEntity(resultSet);
                users.add(optionalUser);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in finding all products", e);
            throw new DaoException();
        }
        return users;
    }

    public ArrayDeque<String> findUserAccessLevel() {
        ArrayDeque<String> accessLevels = new ArrayDeque<>();
        return null;
    }

    @Override
    public User update(User user) throws DaoException {
        return null;
    }

    @Override
    public Optional<User> authenticateDao(String login, String password) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement authenticateStatement = connection.prepareStatement
                     (UserDaoRequest.SELECT_USER_INFO_WITH_ACCESS_LEVEL_BY_LOGIN_AND_PASSWORD)) {
            authenticateStatement.setString(1, login);
            authenticateStatement.setString(2, password);
            try (ResultSet resultSet = authenticateStatement.executeQuery()) {
                // TODO: 18.04.2022 implement try-with-resources for resultSet
                /*String passFromDB;
                passFromDB = resultSet.getString(PASSWORD);*/
                if (resultSet.next()) {
                    optionalUser = UserMapper.getInstance().mapEntity(resultSet);
                    logger.log(Level.INFO, "user was found by login {} and hash password {}",
                            login, password);
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
             PreparedStatement statement = connection.prepareStatement(UserDaoRequest.REGISTER_USER);
             PreparedStatement getUserIdStatement = connection.prepareStatement(UserDaoRequest.GET_USER_ID);
             PreparedStatement accessLevelStatement = connection.prepareStatement
                     (UserDaoRequest.ADD_CLIENT_ACCESS_LEVEL)) {

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
                    userId = resultSet.getString(UserAttribute.USER_ID);
                    accessLevelStatement.setString(1, userId);
                    accessLevelStatement.setString(2,
                            String.valueOf(user.getAccessLevel()));
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

    public Double showMoneyAmountDao(String userId) throws DaoException {
        Double moneyAmount = UserAttribute.INITIAL_MONEY_AMOUNT;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement getMoneyAmountStatement = connection.prepareStatement
                     (OrderDaoRequest.GET_MONEY_AMOUNT_BY_USER_ID)) {
            getMoneyAmountStatement.setString(1, userId);
            try (ResultSet resultSet = getMoneyAmountStatement.executeQuery()) {
                if (resultSet.next()) {
                    moneyAmount = Double.valueOf(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return moneyAmount;
    }

    public void updateMoneyAmountDao(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement updateMoneyAmountStatement = connection.prepareStatement
                     (UPDATE_MONEY_AMOUNT_BY_USER_ID)) {
            updateMoneyAmountStatement.setString(1, String.valueOf(user.getMoneyAmount()));
            updateMoneyAmountStatement.setString(2, String.valueOf(user.getUserId()));
            updateMoneyAmountStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
    }

    public Optional<User> userExistDao(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement checkUserExistStatement = connection.prepareStatement
                     (USER_ALREADY_EXIST_CHECK)) {
            checkUserExistStatement.setString(1, user.getLogin());
            checkUserExistStatement.setString(2, user.getEmail());
            checkUserExistStatement.setString(3, user.getPhone());
            try (ResultSet resultSet = checkUserExistStatement.executeQuery()) {
                while (resultSet.next()) {
                    boolean userExist = Optional.ofNullable(resultSet.getString(1)).isPresent();
                    if (userExist) {
                        User existingUser = new User();
                        String login = resultSet.getString(1);
                        String email = resultSet.getString(2);
                        String phone = resultSet.getString(3);
                        if (login.equals(user.getLogin())) {
                            existingUser.setLogin(login);
                        }
                        if (email.equals(user.getEmail())) {
                            existingUser.setEmail(email);
                        }
                        if (phone.equals(user.getPhone())) {
                            existingUser.setPhone(phone);
                        }
                        return Optional.of(existingUser);
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return Optional.empty();
    }
}
