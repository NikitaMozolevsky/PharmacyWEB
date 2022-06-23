package com.example.demo.dao.impl;

import com.example.demo.controller.listener.SessionAttributeListenerImpl;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.AbstractEntity;
import com.example.demo.entity.order_product.OrderProduct;
import com.example.demo.exception.DaoException;
import com.example.demo.pool.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.demo.dao.request.OrderDaoRequest.ADD_ORDER_PRODUCT;
import static com.example.demo.dao.request.OrderDaoRequest.GET_ORDER_ID_BY_USER_ID;


public class OrderProductDaoImpl extends BaseDao {

    static Logger logger = LogManager.getLogger();

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

    public boolean addOrderProductDao(OrderProduct orderProduct) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_ORDER_PRODUCT);
            PreparedStatement getOrderIdStatement = connection.prepareStatement(GET_ORDER_ID_BY_USER_ID)) {
            String userId = String.valueOf(orderProduct.getUserId());
            getOrderIdStatement.setString(1, userId);
            try(ResultSet resultSet = getOrderIdStatement.executeQuery()) {
                if (resultSet.next()) {
                        String orderId = resultSet.getString(1);
                        statement.setString(1, orderId);
                        statement.setString(2, String.valueOf(orderProduct.getProductId()));
                        statement.setString(3, String.valueOf(orderProduct.getQuantity()));
                        statement.setString(4, String.valueOf(orderProduct.getVolume()));
                        statement.execute();
                        logger.log(Level.INFO, "product was added successful");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "product was not registered");
            throw new DaoException();
        }
        return true;
    }
}
