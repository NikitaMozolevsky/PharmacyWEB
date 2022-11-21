package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.FULL_COST;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.ORDER_ID;

public class SetFullCostInSessionCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        try {
            request.setAttribute(FULL_COST, orderDao.getFullCostDao
                    (String.valueOf(request.getSession().getAttribute(ORDER_ID))));
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException();
        }

        return null;
    }
}
