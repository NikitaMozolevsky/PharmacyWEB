package com.example.demo.validator.impl;

import com.example.demo.validator.PasswordAndLoginValidator;

public class PasswordAndLoginValidatorImpl implements PasswordAndLoginValidator {

    public static final String LOGIN_REGEX = "loginRegex";
    public static final String PASSWORD_REGEX = "passwordRegex";
    private static PasswordAndLoginValidatorImpl passwordAndLoginValidator = new PasswordAndLoginValidatorImpl();

    private PasswordAndLoginValidatorImpl() {
    }

    public static PasswordAndLoginValidatorImpl getInstance() {
        return passwordAndLoginValidator;
    }

    @Override
    public boolean loginCorrect(String login) {
        return login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean passwordCorrect(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}
