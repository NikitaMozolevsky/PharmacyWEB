package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.OrderProductService;
import com.example.demo.service.impl.OrderProductServiceImpl;
import com.example.demo.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.command.constant.PagePath.SHOW_PRODUCTS;
import static com.example.demo.command.constant.ProductAttribute.*;
import static com.example.demo.command.constant.UserAttribute.*;

public class AddProductToCartCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        /*UserServiceImpl userService = UserServiceImpl.getInstance();*/
        OrderProductServiceImpl orderProductService = OrderProductServiceImpl.getInstance();
        Router router = new Router();

        Map<String, String> buyingProductDataMap = new HashMap();
        buyingProductDataMap.put(VOLUME, request.getParameter(VOLUME));
        buyingProductDataMap.put(QUANTITY, request.getParameter(QUANTITY));
        buyingProductDataMap.put(USER_ID, (String) request.getSession().getAttribute(USER_ID));
        buyingProductDataMap.put(PRODUCT_ID, (String) request.getSession().getAttribute(PRODUCT_ID));

        router.setPage(SHOW_PRODUCTS);
        //if (userService.validateUserData(userDataMap)) {
        orderProductService.addProductToCart(buyingProductDataMap);
        //}
        logger.log(Level.INFO, "Product was added to cart successful");
        router.setPage(SHOW_PRODUCTS);
        return router;
    }
}
