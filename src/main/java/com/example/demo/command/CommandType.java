package com.example.demo.command;

import com.example.demo.command.impl.order.AddProductToCartCommand;
import com.example.demo.command.impl.order.BuyProductCommand;
import com.example.demo.command.impl.order.ChooseProductCommand;
import com.example.demo.command.impl.order.CreateOrderCommand;
import com.example.demo.command.impl.product.*;
import com.example.demo.command.impl.user.*;
import com.example.demo.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandType { // TODO: 01.04.2022 Проверить commandStr, его существование (DefaultCommand)
    REGISTER(new RegistrationCommand()),
    ADD_USER(new AddUserCommand()),
    ADD_PRODUCT(new AddProductCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    REGISTER_PAGE(new RegistrationPageCommand()),
    ADD_USER_PAGE(new AddUserPageCommand()),
    ADD_PRODUCT_PAGE(new AddProductPageCommand()),
    SHOW_ALL_USERS(new ShowUserList()),
    SHOW_ALL_PRODUCTS(new ShowProductList()),
    ADD_TO_CART(new AddProductToCartCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    CHOOSE_PRODUCT(new ChooseProductCommand()),
    BUY_PRODUCT(new BuyProductCommand());


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
