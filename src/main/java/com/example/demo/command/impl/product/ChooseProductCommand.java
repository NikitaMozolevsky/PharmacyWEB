package com.example.demo.command.impl.product;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.command.attribute.PagePath;
import com.example.demo.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.demo.command.attribute.ProductAttribute.PRICE;
import static com.example.demo.command.attribute.ProductAttribute.PRODUCT_ID;

public class ChooseProductCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.log(Level.INFO, "parameter price value {}", request.getParameter(PRICE));
        logger.log(Level.INFO, "parameter price value {}", request.getParameterMap());
        request.getSession().setAttribute(PRODUCT_ID, request.getParameter(PRODUCT_ID));
        request.getSession().setAttribute(PRICE, request.getParameter(PRICE));
        return new Router(PagePath.ADD_TO_CART);
    }
}
