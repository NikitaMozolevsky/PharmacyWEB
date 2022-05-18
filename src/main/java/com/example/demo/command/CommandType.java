package com.example.demo.command;

import com.example.demo.command.impl.*;
import com.example.demo.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandType { // TODO: 01.04.2022 Проверить commandStr, его существование (DefaultCommand)
    REGISTER(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    REGISTER_PAGE(new RegistrationPageCommand());

    static Logger logger = LogManager.getLogger();
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) throws CommandException {
        CommandType commandType;
        if (commandStr != null) {// TODO: 18.04.2022 check commandStr for null
            // TODO: 18.04.2022 check for matching types (IllegalArgumentException)
            try {
                commandType = CommandType.valueOf(commandStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.log(Level.ERROR, "incorrect command");
                throw new CommandException(e);
            }
        } else {
            commandType = CommandType.DEFAULT;
        }
        return commandType.command;
    }

    public Command getCommand() {
        return command;
    }
}
