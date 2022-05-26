package com.example.demo.command.impl.user;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.command.constant.PagePath.INDEX;
import static com.example.demo.command.constant.UserAttribute.*;

public class RegistrationCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        Router router = new Router();

        Map<String, String> userDataMap = new HashMap();
        userDataMap.put(USER_NAME, request.getParameter(USER_NAME));
        userDataMap.put(LOGIN, request.getParameter(LOGIN));
        userDataMap.put(EMAIL, request.getParameter(EMAIL));
        userDataMap.put(PHONE, request.getParameter(PHONE));
        userDataMap.put(PASSWORD, request.getParameter(PASSWORD));

        try {
            //if (userService.validateUserData(userDataMap)) {
                userService.register(userDataMap);
                //}
            logger.log(Level.INFO, "user was registered successful");
            router.setPage(INDEX);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "user register error", e);
        }
        return router;
    }
}
