package com.example.demo.service.impl;

import com.example.demo.exception.ServiceException;
import com.example.demo.service.OrderService;

import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private static OrderServiceImpl orderService = new OrderServiceImpl();

    private OrderServiceImpl() {}

    public static OrderServiceImpl getInstance() {
        return orderService;
    }

    @Override
    public boolean addOrder(Map<String, String> newProductData) throws ServiceException {
        return false;
    }

    @Override
    public boolean payOrder(Map<String, String> newProductData) throws ServiceException {
        return false;
    }
}
