package by.mozolevskij.pharmacy.dao;

import by.mozolevskij.pharmacy.entity.user.User;
import by.mozolevskij.pharmacy.exception.DaoException;

import java.util.Optional;

public interface UserDao { // TODO: 18.04.2022 scr. 3

    Optional<User> authenticateDao(String login, String password) throws DaoException;
    void registerDao(User user, String hashPassword) throws DaoException;
}