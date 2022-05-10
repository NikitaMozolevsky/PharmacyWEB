package com.example.demo.validator.impl;

import com.example.demo.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {

    public static final String LOGIN_REGEX = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static UserValidatorImpl userValidator = new UserValidatorImpl();

    private UserValidatorImpl() {
    }

    public static UserValidatorImpl getInstance() {
        return userValidator;
    }

    @Override
    public boolean loginCorrect(String login) {
        return login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean passwordCorrect(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean emailCorrect(String email) {
        return false;
    }

    @Override
    public boolean phoneCorrect(String phone) {
        return false;
    }

    @Override
    public boolean emailAddress(String address) {
        return false;
    }

    @Override
    public boolean userNameCorrect(String userName) {
        return false;
    }
}
