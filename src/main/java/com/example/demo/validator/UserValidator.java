package com.example.demo.validator;

public interface UserValidator {
    boolean loginCorrect(String login);
    boolean passwordCorrect(String password);
    boolean emailCorrect(String email);
    boolean phoneCorrect(String phone);
    boolean emailAddress(String address);
    boolean userNameCorrect(String userName);

}
