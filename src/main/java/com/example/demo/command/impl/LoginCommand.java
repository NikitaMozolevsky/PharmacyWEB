package com.example.demo.command.impl;

import com.example.demo.command.Command;
import com.example.demo.command.PagePath;
import com.example.demo.command.Router;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.demo.command.Attributes.*;

public class LoginCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router = new Router();
        String login = request.getParameter(LOGIN); // TODO: 18.04.2022 add to separate class 2 scr.
        String password = request.getParameter(PASS);
        UserService userService = UserServiceImpl.getInstance();
        /*String page;*/
        HttpSession session = request.getSession();
        try {
            if (userService.authenticate(login, password)) {
                request.setAttribute(USER, login);
                session.setAttribute(USER_NAME, login );
                router.setPage(PagePath.MAIN);
                /*page = PAGES_MAIN_JSP;*/
            } else {
                request.setAttribute(LOGIN_MSG, "incorrect login or pass");
                router.setPage(INDEX);
            }
            /*session.setAttribute(CURRENT_PAGE, page);*/
        } catch (ServiceException e) {
            throw new CommandException("Command exception", e); // TODO: 18.04.2022
        }
        return router; // TODO: 01.04.2022 Validation (in UserServiceImpl), implement Router
    }
}