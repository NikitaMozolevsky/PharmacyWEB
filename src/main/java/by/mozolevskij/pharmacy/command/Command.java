package by.mozolevskij.pharmacy.command;

import by.mozolevskij.pharmacy.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
       Router execute(HttpServletRequest request) throws CommandException; // TODO: 13.04.2022 return Router, inside
       default void refresh(){}
}
