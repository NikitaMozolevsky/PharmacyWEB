package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.command.impl.product.ShowProductListCommand;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.impl.OrderProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.command.attribute.OrderAttribute.ORDER_ID;
import static com.example.demo.command.attribute.ProductAttribute.*;
import static com.example.demo.command.attribute.UserAttribute.USER_ID;

public class AddOrderProductCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        /*UserServiceImpl userService = UserServiceImpl.getInstance();*/
        OrderProductServiceImpl orderProductService = OrderProductServiceImpl.getInstance();
        Router router  = new ShowProductListCommand().execute(request);

        Map<String, String> orderProductDataMap = new HashMap();
        orderProductDataMap.put(QUANTITY, request.getParameter(QUANTITY));
        orderProductDataMap.put(VOLUME, request.getParameter(VOLUME));
        orderProductDataMap.put(USER_ID, (String) request.getSession().getAttribute(USER_ID));
        orderProductDataMap.put(PRODUCT_ID, (String) request.getSession().getAttribute(PRODUCT_ID));

        //if (userService.validateUserData(userDataMap)) {
        try {
            orderProductService.addOrderProductService(orderProductDataMap);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "service exception", e);
            throw new CommandException();
        }
        logger.log(Level.INFO, "Product was added to cart successful");
        return router;
    }
}
