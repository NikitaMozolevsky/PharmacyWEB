package by.mozolevskij.pharmacy.command.impl.user;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.DefaultCommand;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.attribute.OrderAttribute;
import by.mozolevskij.pharmacy.command.attribute.UserAttribute;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.command.impl.product.ShowProductListCommand;
import by.mozolevskij.pharmacy.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.mozolevskij.pharmacy.command.attribute.PagePath.SHOW_PRODUCTS_JSP;

public class TopUpAccountCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.log(Level.INFO, "money amount in the beginning {}", request.getSession()
                .getAttribute(UserAttribute.MONEY_AMOUNT));
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        double moneyAmount = Double.parseDouble(request.getParameter(OrderAttribute.SET_MONEY_AMOUNT));
        User user = new User();
        user.setMoneyAmount(moneyAmount);
        String userId = String.valueOf(request.getSession().getAttribute(UserAttribute.USER_ID));
        user.setUserId(Integer.parseInt(userId));
        try {
            Double finallyMoneyAmount = orderDao.topUpAccountDao(user);
            request.getSession().setAttribute(UserAttribute.MONEY_AMOUNT, finallyMoneyAmount);
            logger.log(Level.INFO, "money amount in the ending {}", request.getSession()
                    .getAttribute(UserAttribute.MONEY_AMOUNT));
            return new DefaultCommand().execute(request);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
    }
}
