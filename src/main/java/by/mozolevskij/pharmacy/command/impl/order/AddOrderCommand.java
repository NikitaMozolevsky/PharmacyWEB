package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.attribute.OrderAttribute;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.impl.OrderServiceImpl;
import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.impl.product.ShowProductListCommand;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.util.StringTimeNow;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.*;

public class AddOrderCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderServiceImpl orderService = OrderServiceImpl.getInstance();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        Router router = new ShowProductListCommand().execute(request);
        Map<String, String> orderInfo = new HashMap<>();
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));
        orderInfo.put(USER_ID, userId);
        orderInfo.put(STATUS, OrderAttribute.CREATING);
        orderInfo.put(DATE_OPEN, StringTimeNow.stringTimeNow());
        orderInfo.put(FULL_COST, String.valueOf(EMPTY_CART_COST));
        try {
            String orderId = orderService.addOrderService(orderInfo);
            request.getSession().setAttribute(ORDER_ID, orderId);
            request.getSession().setAttribute(MONEY_AMOUNT, INITIAL_MONEY_AMOUNT);
            request.getSession().setAttribute(FULL_COST, INITIAL_MONEY_AMOUNT);
            logger.log(Level.INFO, "session attributes {}", request.getSession().getAttributeNames());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        return router;
    }
}
