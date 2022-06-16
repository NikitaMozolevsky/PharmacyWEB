package com.example.demo.dao.impl;

import com.example.demo.dao.BaseDao;
import com.example.demo.entity.AbstractEntity;
import com.example.demo.entity.DrugType;
import com.example.demo.entity.Product;
import com.example.demo.exception.DaoException;
import com.example.demo.pool.ConnectionPool;
import com.example.demo.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.command.constant.ProductAttribute.*;
import static com.example.demo.dao.DaoRequest.ADD_PRODUCT_REQUEST;
import static com.example.demo.dao.DaoRequest.GET_ALL_PRODUCTS;

public class ProductDaoImpl extends BaseDao {

    static Logger logger = LogManager.getLogger();

    private static ProductDaoImpl productDao = new ProductDaoImpl();

    private ProductDaoImpl() {}

    public static ProductDaoImpl getInstance() {
        return productDao;
    }

    public boolean registerDao(Product productData) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_REQUEST)) {

            statement.setString(1, productData.getProductName());
            statement.setString(2, productData.getDetails());
            statement.setString(3, String.valueOf(productData.getPrice()));
            statement.setString(4, String.valueOf(productData.getType()));
            /*statement.setString(5, String.valueOf(productData.getVolume()));*/
            statement.setString(5, productData.getPhoto());

            statement.execute();
            logger.log(Level.INFO, "product was added successful");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "product was not registered");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(AbstractEntity abstractEntity) throws DaoException {
        return false;
    }

    @Override
    public List<Product> findAll() throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt(PRODUCT_ID));
                product.setProductName(resultSet.getString(PRODUCT_NAME));
                product.setDetails(resultSet.getString(DETAILS));
                product.setPrice(resultSet.getDouble(PRICE));
                product.setType(DrugType.valueOf(resultSet.getString(TYPE)));
                /*product.setVolume(resultSet.getString(VOLUME));*/
                product.setPhoto(resultSet.getString(PHOTO));
                products.add(product);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in finding all products", e);
        }
        return products;
    }

    @Override
    public AbstractEntity update(AbstractEntity abstractEntity) throws DaoException {
        return null;
    }
}
