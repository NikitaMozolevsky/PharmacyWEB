package com.example.demo.service.impl;

import com.example.demo.dao.impl.ProductDaoImpl;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.AccessLevel;
import com.example.demo.entity.DrugType;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.ProductService;
import com.example.demo.util.PasswordEncryptor;

import java.util.Map;

import static com.example.demo.command.constant.ProductAttribute.*;
import static com.example.demo.command.constant.UserAttribute.*;
import static com.example.demo.command.constant.UserAttribute.PASSWORD;

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
        product.setVolume(Double.parseDouble(newProductData.get(VOLUME)));//for example
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
    public boolean showAllProducts() {
        return false;
    }

    @Override
    public boolean showProductByName() {
        return false;
    }

    @Override
    public boolean showProductById() {
        return false;
    }
}
