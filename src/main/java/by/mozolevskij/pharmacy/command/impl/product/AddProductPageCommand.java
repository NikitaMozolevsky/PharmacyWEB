package by.mozolevskij.pharmacy.command.impl.product;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class AddProductPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.ADD_PRODUCT);
    }
}
