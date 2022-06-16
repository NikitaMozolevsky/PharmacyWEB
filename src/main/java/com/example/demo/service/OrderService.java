package com.example.demo.service;

import com.example.demo.exception.ServiceException;

import java.util.Map;

public interface OrderService {
    boolean addOrder(Map<String, String> newProductData) throws ServiceException;
    boolean payOrder(Map<String, String> newProductData) throws ServiceException;
}
