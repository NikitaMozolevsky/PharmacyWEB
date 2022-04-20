package com.example.demo.command.impl;

import com.example.demo.command.Command;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.demo.command.Attributes.*;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(LOGIN); // TODO: 18.04.2022 add to separate class 2 scr.
        String password = request.getParameter(PASS);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if (userService.authenticate(login, password)) {
                request.setAttribute(USER, login);
                session.setAttribute(USER_NAME, login );
                page = PAGES_MAIN_JSP;
            } else {
                request.setAttribute(LOGIN_MSG, "incorrect login or pass");
                page = INDEX_JSP;
            }
            session.setAttribute(CURRENT_PAGE, page);
        } catch (ServiceException e) {
            throw new CommandException("Command exception", e); // TODO: 18.04.2022
        }
        return page; // TODO: 01.04.2022 Validation (in UserServiceImpl)
    }
}