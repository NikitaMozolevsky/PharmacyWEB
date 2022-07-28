package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.impl.product.ShowProductListCommand;
import by.mozolevskij.pharmacy.service.impl.OrderProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.FULL_COST;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.ORDER_ID;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.*;

public class AddOrderProductCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        /*UserServiceImpl userService = UserServiceImpl.getInstance();*/
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        logger.log(Level.INFO, "money amount in beginning = {}", request.getSession().getAttribute
                (MONEY_AMOUNT));
        logger.log(Level.INFO, "get parameters = {}", request.getParameterMap());
        OrderProductServiceImpl orderProductService = OrderProductServiceImpl.getInstance();
        Router router = new ShowProductListCommand().execute(request);
        Map<String, String> orderProductDataMap = new HashMap();
        orderProductDataMap.put(ProductAttribute.GOODS_QUANTITY, request.getParameter(ProductAttribute.GOODS_QUANTITY));
        orderProductDataMap.put(ProductAttribute.VOLUME, request.getParameter(ProductAttribute.VOLUME));
        orderProductDataMap.put(USER_ID, (String) request.getSession().getAttribute(USER_ID));
        orderProductDataMap.put(ProductAttribute.PRODUCT_ID, (String) request.getSession().getAttribute(ProductAttribute.PRODUCT_ID));
        String orderId = String.valueOf(request.getSession().getAttribute(ORDER_ID));
        //if (userService.validateUserData(userDataMap)) {
        try {
            orderProductService.addOrderProductService(orderProductDataMap);
            double fullCost = orderDao.getFullCostDao(orderId);
            request.getSession().setAttribute(FULL_COST, fullCost);
        } catch (DaoException | ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        logger.log(Level.INFO, "Product was added to cart successful");
        logger.log(Level.INFO, "money amount in ending in session = {}", request.getSession().getAttribute
                (MONEY_AMOUNT));
        logger.log(Level.INFO, "full cost in ending in session = {}", request.getSession().getAttribute
                (FULL_COST));
        return router;
    }
}
