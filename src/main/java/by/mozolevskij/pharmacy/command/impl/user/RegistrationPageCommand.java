package by.mozolevskij.pharmacy.command.impl.user;

import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class RegistrationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.REGISTER_PAGE_JSP);
    }
}
