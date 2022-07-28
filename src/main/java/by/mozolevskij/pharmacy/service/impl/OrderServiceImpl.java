package by.mozolevskij.pharmacy.service.impl;

import by.mozolevskij.pharmacy.command.attribute.OrderAttribute;
import by.mozolevskij.pharmacy.dao.impl.UserDaoImpl;
import by.mozolevskij.pharmacy.entity.order.Order;
import by.mozolevskij.pharmacy.entity.user.User;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.OrderService;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.entity.order.OrderStatus;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Map;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.MONEY_AMOUNT;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.USER_ID;

public class OrderServiceImpl implements OrderService {

    static Logger logger = LogManager.getLogger();

    private static OrderServiceImpl orderService = new OrderServiceImpl();

    private OrderServiceImpl() {}

    public static OrderServiceImpl getInstance() {
        return orderService;
    }

    @Override
    public String addOrderService(Map<String, String> orderInfo) throws ServiceException {
        Order order = new Order();//for example
        String orderId;
        order.setUserId(Integer.parseInt(orderInfo.get(USER_ID))); // TODO: 5/17/2022 добавить Имя Фамилию ?
        order.setOrderStatus(OrderStatus.valueOf(orderInfo.get(OrderAttribute.STATUS)));
        order.setDateOpen(LocalDateTime.parse(orderInfo.get(OrderAttribute.DATE_OPEN)));
        order.setFullCost(OrderAttribute.EMPTY_CART_COST);

        OrderDaoImpl orderProductDao = OrderDaoImpl.getInstance();
        try {
            orderId = orderProductDao.addOrderDao(order).get();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e);
        }
        return orderId;
    }
    @Override
    public boolean payOrderService(Map<String, String> orderData) throws ServiceException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        Order order = new Order();
        order.setOrderId(Integer.parseInt(orderData.get(ORDER_ID)));
        order.setAddress(orderData.get(ADDRESS));
        order.setOrderStatus(OrderStatus.valueOf(orderData.get(STATUS)));
        order.setDateClose(LocalDateTime.parse(orderData.get(DATE_CLOSE)));
        try {
            orderDao.payForOrderDao(order);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new ServiceException();
        }
        return false;
    }

    public void updateMoneyAmountService(Map<String, String> userData) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        User user = new User();
        user.setMoneyAmount(Double.parseDouble(userData.get(MONEY_AMOUNT)));
        user.setUserId(Integer.parseInt(userData.get(USER_ID)));
        try {
            userDao.updateMoneyAmountDao(user);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new ServiceException();
        }
    }
}
