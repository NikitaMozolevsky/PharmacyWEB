package com.example.demo.command;

import com.example.demo.exception.CommandException;
import com.example.demo.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
       String execute(HttpServletRequest request) throws CommandException; // TODO: 13.04.2022 return Router, inside 
       default void refresh(){}
}
