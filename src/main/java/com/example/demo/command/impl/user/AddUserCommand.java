package com.example.demo.command.impl.user;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.command.attribute.UserAttribute.*;
import static com.example.demo.command.attribute.UserAttribute.PASSWORD;

public class AddUserCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        Router router = new Router();

        Map<String, String> userDataBeingAddedMap = new HashMap<String, String>();
        userDataBeingAddedMap.put(USER_NAME, request.getParameter(USER_NAME));
        userDataBeingAddedMap.put(LOGIN, request.getParameter(LOGIN));
        userDataBeingAddedMap.put(EMAIL, request.getParameter(EMAIL));
        userDataBeingAddedMap.put(PHONE, request.getParameter(PHONE));
        userDataBeingAddedMap.put(PASSWORD, request.getParameter(PASSWORD));
        userDataBeingAddedMap.put(ACCESS_LEVEL, request.getParameter(ACCESS_LEVEL));

        //if (userService.validateUserData(userDataMap)) {
        try {
            userService.addUser(userDataBeingAddedMap);
            logger.log(Level.INFO, "user was registered successful");
            router = new ShowUserListCommand().execute(request);
            /*router.setPage(SHOW_USERS);*/
        }
        catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return router;
    }
}
