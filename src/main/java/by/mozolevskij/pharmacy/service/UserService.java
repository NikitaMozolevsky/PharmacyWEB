package by.mozolevskij.pharmacy.service;

import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.entity.user.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> authenticate(String login, String password) throws ServiceException;
    boolean register(Map<String, String> userData) throws ServiceException;
    boolean addUser(Map<String, String> userData) throws ServiceException;
    boolean validateUserData(Map<String, String> userData) throws ServiceException;
}
