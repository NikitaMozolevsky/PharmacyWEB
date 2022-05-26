package com.example.demo.service;

import com.example.demo.exception.ServiceException;

import java.util.Map;

public interface ProductService {
    boolean addProduct(Map<String, String> newProductData) throws ServiceException;
    boolean showAllProducts() throws ServiceException;
    boolean showProductByName() throws ServiceException;
    boolean showProductById() throws ServiceException;
}
