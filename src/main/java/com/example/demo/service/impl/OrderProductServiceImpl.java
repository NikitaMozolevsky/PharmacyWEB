package com.example.demo.service.impl;

import com.example.demo.service.OrderProductService;

import java.util.Map;

public class OrderProductServiceImpl implements OrderProductService {

    private static OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();

    private OrderProductServiceImpl() {}

    public static OrderProductServiceImpl getInstance() {
        return orderProductService;
    }

    @Override
    public boolean addProductToCart(Map<String, String> orderProductDetails) {
        return false;
    }

    @Override
    public void showAllOrderProduct() {

    }
}
