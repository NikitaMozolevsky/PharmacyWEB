package com.example.demo.command.impl;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.entity.AccessLevel;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.command.PagePath.INDEX;
import static com.example.demo.command.UserAttributes.*;

public class RegistrationCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        Router router = new Router();
        String userName = request.getParameter(USER_NAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        /*AccessLevel accessLevel = AccessLevel.valueOf(request.getParameter(ACCESS_LEVEL));*/
        String email = request.getParameter(EMAIL);
        String phone = request.getParameter(PHONE);

        Map<String, String> userDataMap = new HashMap();
        userDataMap.put(USER_NAME, request.getParameter(USER_NAME));
        userDataMap.put(LOGIN, request.getParameter(LOGIN));
        userDataMap.put(EMAIL, request.getParameter(EMAIL));
        userDataMap.put(PHONE, request.getParameter(PHONE));
        userDataMap.put(PASSWORD, request.getParameter(PASSWORD));

        try {
            if (userService.validateUserData(userDataMap)) {
                userService.register(userDataMap);
            }
            logger.log(Level.INFO, "user was registered successful");
            router.setPage(INDEX);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "user register error", e);
        }
        return router;
    }
}
