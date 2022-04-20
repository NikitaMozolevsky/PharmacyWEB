package com.example.demo.service.impl;

import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.UserService;

public class UserServiceImpl implements UserService { //validators here
    private static UserServiceImpl userService = new UserServiceImpl();

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {
        return userService;
    }
    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        // TODO: 18.04.2022 validate login, pass + шифрование (md5)

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return match;
    }
}
