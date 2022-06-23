package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.dao.impl.OrderDaoImpl;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import static com.example.demo.command.constant.PagePath.SHOW_PRODUCTS;
import static com.example.demo.command.constant.UserAttribute.USER_ID;

public class CreateOrderAndOrderProductCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException, SQLException, DaoException {
        Router router = new Router();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        if (orderDao.isOrderForUserAlreadyExist(request.getParameter(USER_ID))) {

        }
        AddOrderCommand addOrderCommand = new AddOrderCommand();
        addOrderCommand.execute(request);
        AddOrderProductCommand addOrderProductCommand = new AddOrderProductCommand();
        addOrderProductCommand.execute(request);

        logger.log(Level.INFO, "order created");
        router.setPage(SHOW_PRODUCTS);
        return router;
    }
}
