package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.util.AddressGenerator;
import jakarta.servlet.http.HttpServletRequest;

import static by.mozolevskij.pharmacy.command.attribute.PagePath.MAIN_PAGE;
import static by.mozolevskij.pharmacy.command.attribute.PagePath.SET_ADDRESS_JSP;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.*;

public class SetAddressCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPage(SET_ADDRESS_JSP);
        return router;
    }
}
