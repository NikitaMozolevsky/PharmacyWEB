package com.example.demo.command.impl;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        return router;
    }
}
