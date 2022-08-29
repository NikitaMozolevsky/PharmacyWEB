package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.attribute.OrderAttribute;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.dao.impl.OrderProductDaoImpl;
import by.mozolevskij.pharmacy.entity.order.OrderStatus;
import by.mozolevskij.pharmacy.entity.order_product.OrderProduct;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.mozolevskij.pharmacy.command.attribute.Message.CART_IS_EMPTY_MSG;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*;
import static by.mozolevskij.pharmacy.command.attribute.PagePath.MAIN_PAGE_JSP;
import static by.mozolevskij.pharmacy.command.attribute.PagePath.SHOW_CART_JSP;

public class ShowCartCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            String currentOrderId = (String) request.getSession().getAttribute(OrderAttribute.ORDER_ID);
            OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
            if (orderDao.getOrderStatusDao(currentOrderId) == OrderStatus.CLOSED) {
                router.setCurrentPage(MAIN_PAGE_JSP);
                request.setAttribute(CART_IS_EMPTY, CART_IS_EMPTY_MSG);
                return router;
            }
            List<OrderProduct> orderProductList = OrderProductDaoImpl
                    .getInstance().findAllByOrderIdDao(currentOrderId);
            request.setAttribute(ORDER_PRODUCTS, orderProductList);
            router.setCurrentPage(SHOW_CART_JSP);
            logger.log(Level.INFO, "full cost {}", request.getSession().getAttribute(FULL_COST));
        } catch (DaoException e) {
            logger.log(Level.ERROR, "command findAll exception", e);
            throw new CommandException();
        }
        return router;
    }
}
