package com.example.demo.dao;

import com.example.demo.entity.user.User;
import com.example.demo.exception.DaoException;

import java.util.Optional;

public interface UserDao { // TODO: 18.04.2022 scr. 3
    Optional<User> authenticateDao(String login, String password) throws DaoException;
    boolean registerDao(User user, String hashPassword) throws DaoException;
}