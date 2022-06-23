package com.example.demo.service.impl;

import com.example.demo.dao.impl.OrderDaoImpl;
import com.example.demo.dao.impl.OrderProductDaoImpl;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderStatus;
import com.example.demo.entity.order_product.OrderProduct;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.OrderService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import static com.example.demo.command.constant.OrderAttribute.*;
import static com.example.demo.command.constant.ProductAttribute.*;
import static com.example.demo.command.constant.UserAttribute.USER_ID;

public class OrderServiceImpl implements OrderService {

    private static OrderServiceImpl orderService = new OrderServiceImpl();

    private OrderServiceImpl() {}

    public static OrderServiceImpl getInstance() {
        return orderService;
    }

    @Override
    public boolean addOrderService(Map<String, String> orderInfo) throws ServiceException {
        Order order = new Order();//for example
        order.setUserId(Integer.parseInt(orderInfo.get(USER_ID))); // TODO: 5/17/2022 добавить Имя Фамилию ?
        order.setOrderStatus(OrderStatus.valueOf(orderInfo.get(STATUS)));
        order.setDateOpen(LocalDateTime.parse(orderInfo.get(DATE_OPEN)));
        order.setFullCost(EMPTY_CART_COST);

        OrderDaoImpl orderProductDao = new OrderDaoImpl();
        try {
            try {
                orderProductDao.addOrderDao(order);
            } catch (SQLException e) {
                throw new DaoException();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public boolean payOrder(Map<String, String> newProductData) throws ServiceException {
        return false;
    }
}
