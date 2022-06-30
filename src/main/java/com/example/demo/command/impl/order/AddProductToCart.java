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

import static com.example.demo.command.attribute.PagePath.SHOW_PRODUCTS;
import static com.example.demo.command.attribute.UserAttribute.USER_ID;

public class AddProductToCart implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));
        try {
            if (orderDao.isOrderForUserIsNotExist(userId)) {
                AddOrderCommand addOrderCommand = new AddOrderCommand();
                addOrderCommand.execute(request);
                logger.log(Level.INFO, "order for user was created");
            }
            AddOrderProductCommand addOrderProductCommand = new AddOrderProductCommand();
            addOrderProductCommand.execute(request);

            logger.log(Level.INFO, "order created");
            router.setPage(SHOW_PRODUCTS);
            return router;
        }
        catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
    }
}
