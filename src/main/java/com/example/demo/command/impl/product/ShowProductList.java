package com.example.demo.command.impl.product;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.dao.impl.ProductDaoImpl;
import com.example.demo.entity.Product;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.command.constant.PagePath.SHOW_PRODUCTS;
import static com.example.demo.command.constant.ProductAttribute.PRODUCT;

public class ShowProductList implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            List<Product> productList = ProductDaoImpl.getInstance().findAll();
            request.setAttribute(PRODUCT, productList);
            router.setPage(SHOW_PRODUCTS);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "command findAll exception", e);
        }
        return router;
    }
}
