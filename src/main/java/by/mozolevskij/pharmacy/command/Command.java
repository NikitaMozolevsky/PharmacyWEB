package by.mozolevskij.pharmacy.command;

import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
       Router execute(HttpServletRequest request) throws CommandException, DaoException; // TODO: 13.04.2022 return Router, inside
       default void refresh(){}
}
