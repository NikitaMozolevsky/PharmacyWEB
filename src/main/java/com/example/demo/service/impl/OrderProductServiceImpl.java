package com.example.demo.service.impl;

import com.example.demo.dao.impl.OrderProductDaoImpl;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.order_product.OrderProduct;
import com.example.demo.entity.user.AccessLevel;
import com.example.demo.entity.user.User;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.OrderProductService;
import com.example.demo.util.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.example.demo.command.constant.OrderAttribute.ORDER_ID;
import static com.example.demo.command.constant.ProductAttribute.*;
import static com.example.demo.command.constant.UserAttribute.*;

public class OrderProductServiceImpl implements OrderProductService {

    private static OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();

    private OrderProductServiceImpl() {}

    public static OrderProductServiceImpl getInstance() {
        return orderProductService;
    }

    @Override
    public boolean addOrderProductService(Map<String, String> orderProductInfo) throws ServiceException {
        OrderProduct orderProduct = new OrderProduct();//for example
        orderProduct.setProductId(Integer.parseInt(orderProductInfo.get(PRODUCT_ID))); // TODO: 5/17/2022 добавить Имя Фамилию ?
        orderProduct.setUserId(Integer.parseInt(orderProductInfo.get(USER_ID))); // TODO: 5/17/2022 добавить Имя Фамилию ?
        orderProduct.setQuantity(Integer.parseInt(orderProductInfo.get(QUANTITY)));
        orderProduct.setVolume(orderProductInfo.get(VOLUME));

        OrderProductDaoImpl orderProductDao = new OrderProductDaoImpl();
        try {
            orderProductDao.addOrderProductDao(orderProduct);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public void showAllOrderProduct() {

    }
}
