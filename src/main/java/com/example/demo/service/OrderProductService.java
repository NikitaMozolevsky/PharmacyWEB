package com.example.demo.service;

import com.example.demo.exception.ServiceException;

import java.util.Map;

public interface OrderProductService {
    boolean addOrderProductService(Map<String, String> orderProductDetails) throws ServiceException;
    void showAllOrderProduct();
}
