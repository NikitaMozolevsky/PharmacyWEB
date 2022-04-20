package com.example.demo.service.impl;

import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.service.UserService;

public class UserServiceImpl implements UserService { //validators here
    private static UserServiceImpl userService = new UserServiceImpl();

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {
        return userService;
    }
    @Override
    public boolean authenticate(String login, String password) {
        //validate login, pass + шифрование (md5)
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = userDao.authenticate(login, password);
        return match; //todo
    }
}
