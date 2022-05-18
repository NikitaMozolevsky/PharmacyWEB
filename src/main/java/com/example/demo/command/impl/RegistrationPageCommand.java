package com.example.demo.command.impl;

import com.example.demo.command.Command;
import com.example.demo.command.PagePath;
import com.example.demo.command.Router;
import com.example.demo.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class RegistrationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.REGISTER);
    }
}
