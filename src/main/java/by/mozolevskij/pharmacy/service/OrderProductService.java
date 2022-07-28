package by.mozolevskij.pharmacy.service;

import by.mozolevskij.pharmacy.exception.ServiceException;

import java.util.Map;

public interface OrderProductService {
    Double addOrderProductService(Map<String, String> orderProductDetails) throws ServiceException;
    void showAllOrderProduct();
}
