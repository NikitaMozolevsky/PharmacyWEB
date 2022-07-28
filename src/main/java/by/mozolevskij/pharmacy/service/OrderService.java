package by.mozolevskij.pharmacy.service;

import by.mozolevskij.pharmacy.exception.ServiceException;

import java.util.Map;

public interface OrderService {
    String addOrderService(Map<String, String> newProductData) throws ServiceException;
    boolean payOrderService(Map<String, String> orderData) throws ServiceException;
}
