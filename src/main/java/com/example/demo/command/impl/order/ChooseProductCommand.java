package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.command.constant.PagePath;
import com.example.demo.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.demo.command.constant.ProductAttribute.PRODUCT_ID;
import static com.example.demo.command.constant.ProductAttribute.PRODUCT_NAME;

public class ChooseProductCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute(PRODUCT_ID, request.getParameter(PRODUCT_ID));
        return new Router(PagePath.ADD_TO_CART);
    }
}
