package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.dao.impl.OrderProductDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*;

public class RemoveFromCartCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String orderProductId = request.getParameter(ORDER_PRODUCT_ID);
        String orderId = request.getParameter(ORDER_ID);
        OrderProductDaoImpl orderProductDao = OrderProductDaoImpl.getInstance();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        try {
            orderProductDao.delete(orderProductId);
            double orderFullCost = orderDao.getFullCostDao(orderId);
            request.getSession().setAttribute(FULL_COST, orderFullCost);
            logger.log(Level.INFO, "order product with ID = {} was removed successful",
                    orderProductId);
        }
        catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        return new ShowCartCommand().execute(request);
    }
}
