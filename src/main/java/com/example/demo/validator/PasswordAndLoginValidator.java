package com.example.demo.validator;

public interface PasswordAndLoginValidator {
    boolean loginCorrect(String login);
    boolean passwordCorrect(String password);
}
