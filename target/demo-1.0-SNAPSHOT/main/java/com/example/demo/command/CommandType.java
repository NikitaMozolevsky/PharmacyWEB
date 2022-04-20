package com.example.demo.command;

import com.example.demo.command.impl.AddUserCommand;
import com.example.demo.command.impl.DefaultCommand;
import com.example.demo.command.impl.LoginCommand;
import com.example.demo.command.impl.LogoutCommand;

import java.util.Locale;

public enum CommandType { // TODO: 01.04.2022 Проверить commandStr, его существование (DefaultCommand)
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
        return commandType.command;
    }

    public Command getCommand() {
        return command;
    }
}
