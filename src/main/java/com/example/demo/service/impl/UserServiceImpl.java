package com.example.demo.service.impl;

import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.AccessLevel;
import com.example.demo.entity.User;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.UserService;
import com.example.demo.util.PasswordEncryptor;
import com.example.demo.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.example.demo.command.constant.UserAttribute.*;

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
            match = userDao.authenticateDao(login, password);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "user or login don't match in DB", e);
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean register(Map<String, String> userData) throws ServiceException {
        User user = new User();
        String hashPassword;//for example
        user.setUserName(userData.get(USER_NAME)); // TODO: 5/17/2022 добавить Имя Фамилию ?
        user.setLogin(userData.get(LOGIN));
        /*user.setPassword(userData.get(PasswordEncryptor.passwordEncryption(userData.get(PASSWORD))));*/
        user.setEmail(userData.get(EMAIL));
        user.setPhone(userData.get(PHONE));
        user.setAccessLevel(AccessLevel.valueOf("CLIENT"));//for example
        user.setMoneyAmount(0.00);//for example

        hashPassword = PasswordEncryptor.passwordEncryption(userData.get(PASSWORD));

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            userDao.registerDao(user, hashPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean addUser(Map<String, String> userData) throws ServiceException {
        User user = new User();
        String hashPassword;//for example
        user.setUserName(userData.get(USER_NAME)); // TODO: 5/17/2022 добавить Имя Фамилию ?
        user.setLogin(userData.get(LOGIN));
        user.setEmail(userData.get(EMAIL));
        user.setPhone(userData.get(PHONE));
        user.setAccessLevel(AccessLevel.valueOf(userData.get(ACCESS_LEVEL)));//for example
        user.setMoneyAmount(0.00);//for example

        hashPassword = PasswordEncryptor.passwordEncryption(userData.get(PASSWORD));

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            userDao.registerDao(user, hashPassword);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "exception adding a service product", e);
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public boolean validateUserData(Map<String, String> userData) throws ServiceException {
        UserValidatorImpl userValidator = UserValidatorImpl.getInstance();
        boolean isCorrect = true;

        for (Map.Entry<String, String> entry : userData.entrySet()) {
            String key = entry.getKey();
            switch (key) {
                case USER_NAME -> {
                    if (!userValidator.userNameCorrect(entry.getValue())) {
                        isCorrect = false;
                    }
                }
                case LOGIN -> {
                    if (!userValidator.loginCorrect(entry.getValue())) {
                        isCorrect = false;
                    }
                }
                case EMAIL -> {
                    if (!userValidator.emailCorrect(entry.getValue())) {
                        isCorrect = false;
                    }
                }
                case PHONE -> {
                    if (!userValidator.phoneCorrect(entry.getValue())) {
                        isCorrect = false;
                    }
                }
                case PASSWORD -> {
                    if (!userValidator.passwordCorrect(entry.getValue())) {
                        isCorrect = false;
                    }
                }
            }
        }
        if (!isCorrect) {
            logger.log(Level.ERROR, "incorrect user parameters");
        }
        return isCorrect;
}

    /*public Router checkUserAccessLevel(AccessLevel accessLevel) {
        Router router = new Router();
        switch (accessLevel) {
            case CLIENT -> router.setPage(PagePath.CLIENT_PAGE);
        }
        switch (accessLevel) {
            case DOCTOR -> router.setPage(PagePath.DOCTOR_PAGE);
        }
        return router;
    }*/



    /*@Override
    public boolean validateUserData(Map<String, String> dataMap) {
        UserValidatorImpl userValidator = UserValidatorImpl.getInstance();
        if (!userValidator.userNameCorrect(userName)
                || !userValidator.loginCorrect(login)
                || !userValidator.passwordCorrect(password)
                || !userValidator.emailCorrect(email)
                || !userValidator.phoneCorrect(phone)) {
            return false;
        } else {
            return true;
        }
    }*/
}
