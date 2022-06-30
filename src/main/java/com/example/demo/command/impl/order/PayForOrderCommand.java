package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.command.attribute.PagePath;
import com.example.demo.dao.impl.OrderProductDaoImpl;
import com.example.demo.entity.order_product.OrderProduct;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.example.demo.command.attribute.OrderAttribute.ORDER_ID;
import static com.example.demo.command.attribute.OrderAttribute.ORDER_PRODUCTS;

public class PayForOrderCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            String currentOrderId = (String) request.getSession().getAttribute(ORDER_ID);
            List<OrderProduct> orderProductList = OrderProductDaoImpl.getInstance().findAllByOrderId(currentOrderId);
            request.setAttribute(ORDER_PRODUCTS, orderProductList);
            router.setPage(PagePath.PAYING_FOR_GOODS_JSP);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "command findAll exception", e);
            throw new CommandException();
        }
        return router;
    }
}
