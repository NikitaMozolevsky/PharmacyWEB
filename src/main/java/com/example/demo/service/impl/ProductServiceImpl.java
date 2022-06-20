package com.example.demo.service.impl;

import com.example.demo.dao.impl.ProductDaoImpl;
import com.example.demo.entity.product.DrugType;
import com.example.demo.entity.product.Product;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.ProductService;

import java.util.Map;

import static com.example.demo.command.constant.ProductAttribute.*;

public class ProductServiceImpl implements ProductService {

    private static ProductServiceImpl productService = new ProductServiceImpl();

    private ProductServiceImpl() {}

    public static ProductServiceImpl getInstance() {
        return productService;
    }

    @Override
    public boolean addProduct(Map<String, String> newProductData) {
        Product product = new Product();//for example
        product.setProductName(newProductData.get(PRODUCT_NAME)); // TODO: 5/17/2022 добавить Имя Фамилию ?
        product.setDetails(newProductData.get(DETAILS));
        product.setPrice(Double.parseDouble(newProductData.get(PRICE)));
        product.setType(DrugType.valueOf(newProductData.get(TYPE)));
        /*product.setVolume(newProductData.get(VOLUME));*///for example
        product.setPhoto(newProductData.get(PHOTO));//for example

        ProductDaoImpl productDao = ProductDaoImpl.getInstance();
        try {
            productDao.registerDao(product);
        } catch (DaoException e) {
            try {
                throw new ServiceException(e);
            } catch (ServiceException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean addProductToCart(Map<String, String> orderProductInfo) throws ServiceException {
        Product product = new Product();//for example
        product.setProductName(orderProductInfo.get(PRODUCT_NAME)); // TODO: 5/17/2022 добавить Имя Фамилию ?
        product.setDetails(orderProductInfo.get(DETAILS));
        product.setPrice(Double.parseDouble(orderProductInfo.get(PRICE)));
        product.setType(DrugType.valueOf(orderProductInfo.get(TYPE)));
        product.setVolume(orderProductInfo.get(VOLUME));//for example
        product.setPhoto(orderProductInfo.get(PHOTO));//for example

        ProductDaoImpl productDao = ProductDaoImpl.getInstance();
        try {
            productDao.registerDao(product);
        } catch (DaoException e) {
            try {
                throw new ServiceException(e);
            } catch (ServiceException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean showAllProducts() throws ServiceException {
        return false;
    }

    @Override
    public boolean showProductByName() throws ServiceException {
        return false;
    }

    @Override
    public boolean showProductById() throws ServiceException {
        return false;
    }
}
