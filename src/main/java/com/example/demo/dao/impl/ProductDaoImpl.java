package com.example.demo.dao.impl;

import com.example.demo.dao.BaseDao;
import com.example.demo.entity.AbstractEntity;
import com.example.demo.entity.Product;
import com.example.demo.exception.DaoException;
import com.example.demo.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.example.demo.dao.DaoRequest.ADD_PRODUCT_REQUEST;

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
            statement.setString(5, String.valueOf(productData.getVolume()));
            statement.setString(6, productData.getPhoto());

            statement.execute();
            logger.log(Level.INFO, "product was added successful");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "product was not registered");
        }
        return true;
    }

    @Override
    public boolean delete(AbstractEntity abstractEntity) throws DaoException {
        return false;
    }

    @Override
    public List findAll() throws DaoException {
        return null;
    }

    @Override
    public AbstractEntity update(AbstractEntity abstractEntity) throws DaoException {
        return null;
    }
}
