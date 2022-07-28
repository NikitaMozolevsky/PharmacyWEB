package by.mozolevskij.pharmacy.validator.impl;

import by.mozolevskij.pharmacy.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {

    public static final String USER_NAME_REGEX = "^[A-Za-zА-Яа-я]{3,20}$";
    public static final String LOGIN_REGEX = "^[A-Za-zА-Яа-я0-9_]{4,16}$";
    public static final String PASSWORD_REGEX = "^[A-Za-zА-Яа-я0-9_!@#,\\.]{6,16}$";
    public static final String EMAIL_REGEX = "^[^[\\d\\.]][A-Za-z\\.\\d]{1,30}@[a-z]{2,10}\\.([a-z]{2,4}|[a-z]{2,4}\\.[a-z]{2,4})$";
    public static final String PHONE_REGEX = "^\\((025|029|044)\\)\\d{7}$";
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
        return email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean phoneCorrect(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    @Override
    public boolean userNameCorrect(String userName) {
        return userName.matches(USER_NAME_REGEX);
    }
}
