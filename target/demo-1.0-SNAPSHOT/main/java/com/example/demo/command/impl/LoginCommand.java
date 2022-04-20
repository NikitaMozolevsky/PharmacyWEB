package com.example.demo.command.impl;

import com.example.demo.command.Command;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        if (userService.authenticate(login, password)) {
            request.setAttribute("user", login);
            page = "pages/main.jsp";
        } else {
            request.setAttribute("login_msg", "incorrect login or pass");
            page = "index.jsp";
        }
        return page; // TODO: 01.04.2022 Validation (in UserServiceImpl)
    }
}
