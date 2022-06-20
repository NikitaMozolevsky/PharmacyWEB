package com.example.demo.service;

import com.example.demo.entity.user.User;
import com.example.demo.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> authenticate(String login, String password) throws ServiceException;
    boolean register(Map<String, String> userData) throws ServiceException;
    boolean addUser(Map<String, String> userData) throws ServiceException;
    boolean validateUserData(Map<String, String> userData) throws ServiceException;
}
