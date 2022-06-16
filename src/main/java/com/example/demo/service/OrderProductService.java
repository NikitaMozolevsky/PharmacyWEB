package com.example.demo.service;

import java.util.Map;

public interface OrderProductService {
    boolean addProductToCart(Map<String, String> orderProductDetails);
    void showAllOrderProduct();
}
