package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.ORDER_ID;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.USER_ID;

public class AddProductToCart implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));
        try {
            if (orderDao.isOrderForUserIsNotExistDao(userId)) {
                AddOrderCommand addOrderCommand = new AddOrderCommand();
                addOrderCommand.execute(request);
                logger.log(Level.INFO, "order for user was created");
            }
            AddOrderProductCommand addOrderProductCommand = new AddOrderProductCommand();
            addOrderProductCommand.execute(request);

            logger.log(Level.INFO, "order created");
            router.setPage(PagePath.SHOW_PRODUCTS);
            return router;
        }
        catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
    }
}
