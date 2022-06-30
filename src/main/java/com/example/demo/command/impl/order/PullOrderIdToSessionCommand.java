package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.dao.impl.OrderDaoImpl;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.demo.command.attribute.OrderAttribute.ORDER_ID;

public class PullOrderIdToSessionCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        try {
            request.getSession().setAttribute(ORDER_ID, orderDao.getOrderID(request));
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        return null;
    }
}
