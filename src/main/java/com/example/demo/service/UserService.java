package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ServiceException;

import java.util.Map;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean register(Map<String, String> userData) throws ServiceException;
    boolean addUser(Map<String, String> userData) throws ServiceException;
    boolean validateUserData(Map<String, String> userData) throws ServiceException;
}
