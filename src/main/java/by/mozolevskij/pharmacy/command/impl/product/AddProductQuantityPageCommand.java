package by.mozolevskij.pharmacy.command.impl.product;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static by.mozolevskij.pharmacy.command.attribute.PagePath.SET_PRODUCT_QUANTITY_JSP;

public class AddProductQuantityPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setCurrentPage(SET_PRODUCT_QUANTITY_JSP);
        return router;
    }
}
