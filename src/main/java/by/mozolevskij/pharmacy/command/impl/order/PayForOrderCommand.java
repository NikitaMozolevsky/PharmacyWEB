package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.dao.impl.UserDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.service.impl.OrderServiceImpl;
import by.mozolevskij.pharmacy.util.AddressGenerator;
import by.mozolevskij.pharmacy.util.StringTimeNow;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.mozolevskij.pharmacy.command.attribute.Message.*;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*;
import static by.mozolevskij.pharmacy.command.attribute.PagePath.*;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.*;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.HOME;

public class PayForOrderCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        AddressGenerator addressGenerator = new AddressGenerator();
        String country = request.getParameter(COUNTRY);
        String city = request.getParameter(CITY);
        String street = request.getParameter(STREET);
        String home = request.getParameter(HOME);
        String address = addressGenerator.addressGen(country, city, street, home);
        OrderServiceImpl orderService = OrderServiceImpl.getInstance();
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));
        String orderId = String.valueOf(request.getSession().getAttribute(ORDER_ID));
        try {
            double moneyAmount = userDao.showMoneyAmountDao(userId);
            double fullCost = orderDao.getFullCostDao(orderId);
            Optional<List<String>> insufficientProducts = orderDao.getSufficientGoodsQuantity(orderId);
            boolean notEnoughMoney = fullCost > moneyAmount;
            boolean notEnoughGoods = !insufficientProducts.get().isEmpty();
            if (notEnoughMoney || notEnoughGoods) {
                router.setCurrentPage(SET_ADDRESS_JSP);
                if (notEnoughMoney) {
                    request.setAttribute(NOT_ENOUGH_MONEY, NOT_ENOUGH_MONEY_MSG);
                } else {
                    request.setAttribute(NOT_ENOUGH_GOODS, NOT_ENOUGH_GOODS_MSG);
                    String s = (String) request.getAttribute(NOT_ENOUGH_GOODS);
                    List<String> stringList = insufficientProducts.get();
                    request.setAttribute(NOT_ENOUGH_GOODS_NAMES, stringList);
                }
                return router;
                /*router = new SetAddressCommand().execute(request);*/
            } else {
                try {
                    Map<String, String> userMap = new HashMap<>();
                    userMap.put(USER_ID, userId);
                    userMap.put(MONEY_AMOUNT, String.valueOf(moneyAmount - fullCost));
                    orderService.updateMoneyAmountService(userMap);
                    Map<String, String> orderMap = new HashMap<>();
                    orderMap.put(ORDER_ID, orderId);
                    orderMap.put(ADDRESS, address);
                    orderMap.put(STATUS, CLOSED);
                    orderMap.put(DATE_CLOSE, StringTimeNow.stringTimeNow());
                    orderService.payOrderService(orderMap);
                    router.setCurrentPage(MAIN_PAGE_JSP);
                    request.setAttribute(PURCHASE_COMPLETED, PURCHASE_COMPLETED_MSG);
                    request.getSession().setAttribute(MONEY_AMOUNT, String.valueOf(moneyAmount - fullCost));
                    request.getSession().setAttribute(FULL_COST, INITIAL_MONEY_AMOUNT);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e.getMessage());
                    throw new CommandException();
                }
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }

        return router;

    }
}
