package com.example.demo.command.impl.product;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.exception.CommandException;
import com.example.demo.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.command.constant.ProductAttribute.*;

public class AddProductCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        Router router = new Router();

        Map<String, String> newProductData = new HashMap<String, String>();
        newProductData.put(PRODUCT_NAME, request.getParameter(PRODUCT_NAME));
        newProductData.put(DETAILS, request.getParameter(DETAILS));
        newProductData.put(PRICE, request.getParameter(PRICE));
        newProductData.put(TYPE, request.getParameter(TYPE));
        /*newProductData.put(VOLUME, request.getParameter(VOLUME));*/
        newProductData.put(PHOTO, request.getParameter(PHOTO));

        //if (userService.validateUserData(userDataMap)) {
        productService.addProduct(newProductData);
        //}
        logger.log(Level.INFO, "product was added successful");
        router = new ShowProductListCommand().execute(request);
        /*router.setPage(SHOW_PRODUCTS);*/
        return router;
    }
}
