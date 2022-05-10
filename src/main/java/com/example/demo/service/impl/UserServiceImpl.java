package com.example.demo.service.impl;

import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.User;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.UserService;
import com.example.demo.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    //validators here
    static Logger logger = LogManager.getLogger();
    private static UserServiceImpl userService = new UserServiceImpl();
    /*private UserDaoImpl userDao = UserDaoImpl.getInstance();*/

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {
        return userService;
    }
    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        boolean loginIsCorrect = UserValidatorImpl.getInstance().loginCorrect(login);
        boolean passwordIsCorrect = UserValidatorImpl.getInstance().passwordCorrect(password);
        // TODO: 18.04.2022 validate login, pass + шифрование (md5)
        /*if (!loginIsCorrect||!passwordIsCorrect) {
            logger.log(Level.ERROR, "incorrect format login or password");
            throw new ServiceException("incorrect format login or password");
        }*/
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "user or login don't match in DB", e);
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean register(User user) throws ServiceException {
        return false;
    }
}
