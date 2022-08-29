package by.mozolevskij.pharmacy.command;

import by.mozolevskij.pharmacy.command.impl.order.*;
import by.mozolevskij.pharmacy.command.impl.product.*;
import by.mozolevskij.pharmacy.command.impl.order.RequestPrescriptionCommand;
import by.mozolevskij.pharmacy.command.impl.user.*;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.command.impl.product.ChooseProductCommand;
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
    SHOW_ALL_USERS(new ShowUserListCommand()),
    SHOW_ALL_PRODUCTS(new ShowProductListCommand()),
    ADD_ORDER_PRODUCT_TO_CART(new AddOrderProductCommand()),
    ADD_PRODUCT_TO_CART(new AddProductToCart()),
    ADD_PRODUCT_QUANTITY_PAGE(new AddProductQuantityPageCommand()),
    ADD_PRODUCT_QUANTITY(new AddProductQuantityCommand()),
    REQUEST_PRESCRIPTION(new RequestPrescriptionCommand()),
    PRESCRIPTION_REQUEST_LIST_PAGE(new PrescriptionRequestListPageCommand()),
    PRESCRIPTION_RESPONSE_LIST_PAGE(new PrescriptionResponseListPageCommand()),
    CHOOSE_PRODUCT(new ChooseProductCommand()),
    SHOW_CART(new ShowCartCommand()),
    PAY_FOR_ORDER(new PayForOrderCommand()),
    GO_TO_TOP_UP_ACCOUNT(new GoToTopUpAccountCommand()),
    REMOVE_FROM_CART(new RemoveFromCartCommand()),
    SET_ADDRESS(new SetAddressCommand()),
    TOP_UP_ACCOUNT(new TopUpAccountCommand());



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
