package com.example.demo.dao;

import com.example.demo.exception.DaoException;

public interface UserDao { // TODO: 18.04.2022 scr. 3
    boolean authenticate(String login, String password) throws DaoException;
}