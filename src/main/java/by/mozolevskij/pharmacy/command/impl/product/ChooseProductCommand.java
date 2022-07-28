package by.mozolevskij.pharmacy.command.impl.product;

import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRICE;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCT_ID;

public class ChooseProductCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute(PRODUCT_ID, request.getParameter(PRODUCT_ID));
        request.getSession().setAttribute(PRICE, request.getParameter(PRICE));
        return new Router(PagePath.ADD_TO_CART);
    }
}
