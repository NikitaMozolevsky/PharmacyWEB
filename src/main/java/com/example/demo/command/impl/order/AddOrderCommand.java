package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.command.impl.product.ShowProductListCommand;
import com.example.demo.dao.impl.OrderDaoImpl;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.impl.OrderServiceImpl;
import com.example.demo.util.StringTimeNow;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.command.constant.OrderAttribute.*;
import static com.example.demo.command.constant.UserAttribute.USER_ID;

public class AddOrderCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException, SQLException, DaoException {
        OrderServiceImpl orderService = OrderServiceImpl.getInstance();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        Router router = new ShowProductListCommand().execute(request);
        Map<String, String> orderInfo = new HashMap<>();
        Enumeration<String> s = request.getSession().getAttributeNames();
        orderInfo.put(USER_ID, (String) request.getSession().getAttribute(USER_ID));
        orderInfo.put(STATUS, CREATING);
        orderInfo.put(DATE_OPEN, StringTimeNow.stringTimeNow());
        orderInfo.put(FULL_COST, String.valueOf(EMPTY_CART_COST));
        try {
            orderService.addOrderService(orderInfo);
            request.getSession().setAttribute(ORDER_ID, orderDao.getOrderID(request));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "AddInfoToOrder ex.", e);
            throw new ServiceException();
        }
        return router;
    }
}
