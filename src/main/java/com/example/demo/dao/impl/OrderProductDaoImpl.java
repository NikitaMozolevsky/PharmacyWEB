package com.example.demo.dao.impl;

import com.example.demo.controller.listener.SessionAttributeListenerImpl;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.AbstractEntity;
import com.example.demo.entity.order_product.OrderProduct;
import com.example.demo.entity.product.DrugType;
import com.example.demo.entity.product.Product;
import com.example.demo.exception.DaoException;
import com.example.demo.pool.ConnectionPool;
import com.example.demo.util.VolumeToNumber;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.command.attribute.ProductAttribute.*;
import static com.example.demo.command.attribute.ProductAttribute.PHOTO;
import static com.example.demo.dao.request.OrderDaoRequest.*;
import static com.example.demo.dao.request.ProductDaoRequest.GET_ALL_PRODUCTS;
import static com.example.demo.dao.request.ProductDaoRequest.GET_PRODUCT_PRICE_BY_ID;
import static com.example.demo.entity.order.OrderStatus.IN_PROCESS;


public class OrderProductDaoImpl extends BaseDao {

    private static OrderProductDaoImpl orderProductDao = new OrderProductDaoImpl();

    private OrderProductDaoImpl() {}

    public static OrderProductDaoImpl getInstance() {
        return orderProductDao;
    }

    static Logger logger = LogManager.getLogger();

    @Override
    public boolean delete(AbstractEntity abstractEntity) throws DaoException {
        return false;
    }

    @Override
    public List<OrderProduct> findAll() throws DaoException {
        return null;
    }

    public List<OrderProduct> findAllByOrderId(String currentOrderId) throws DaoException {
        List<OrderProduct> products = new ArrayList<>();
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
                /*products.add(product);*/
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

    public boolean addOrderProductDao(OrderProduct orderProduct) throws DaoException {
        try (Connection connectionFirst = ConnectionPool.getInstance().getConnection();
             Connection connectionSecond = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connectionFirst.prepareStatement(ADD_ORDER_PRODUCT);
            PreparedStatement getOrderIdStatement = connectionFirst.prepareStatement
                    (GET_ORDER_ID_BY_USER_ID);
             PreparedStatement getProductPriceByProductIdStatement = connectionSecond.prepareStatement
                     (GET_PRODUCT_PRICE_BY_ID)) {
            VolumeToNumber volumeToNumber = new VolumeToNumber();
            String userId = String.valueOf(orderProduct.getUserId());
            getOrderIdStatement.setString(1, userId);
            try(ResultSet resultSet = getOrderIdStatement.executeQuery()) {
                if (resultSet.next()) {
                        String orderId = resultSet.getString(1);
                        String productId =  String.valueOf(orderProduct.getProductId());
                        int orderProductQuantity = orderProduct.getQuantity();
                        String orderProductVolume = orderProduct.getVolume();
                        getProductPriceByProductIdStatement.setString(1, productId);
                        statement.setString(1, orderId);
                        statement.setString(2, productId);
                        statement.setString(3, String.valueOf(orderProductQuantity));
                        statement.setString(4, orderProductVolume);
                        try(ResultSet secondResultSet = getProductPriceByProductIdStatement.executeQuery()) {
                            if (secondResultSet.next()) {
                                String productPrice = secondResultSet.getString(1);
                                int orderProductVolumeInt = volumeToNumber.convert(orderProductVolume);
                                double orderProductPrice = Double.parseDouble(productPrice)*
                                        orderProductVolumeInt*orderProductQuantity;
                                statement.setString(5, String.valueOf(orderProductPrice));
                            }
                        }
                        statement.execute();
                        logger.log(Level.INFO, "product was added successful");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "product was not registered {}", e.getErrorCode());
            throw new DaoException();
        }
        return true;
    }

    public boolean setOrderStatusInProcess(OrderProduct orderProduct) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_ORDER_STATUS_IN_PROCESS);
        PreparedStatement getOrderIdStatement = connection.prepareStatement
                     (GET_ORDER_ID_BY_USER_ID)) {
            String userId = String.valueOf(orderProduct.getUserId());
            getOrderIdStatement.setString(1, userId);
            try(ResultSet resultSet = getOrderIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    String orderId = resultSet.getString(1);
                    statement.setString(1, String.valueOf(IN_PROCESS));
                    statement.setString(2, orderId);
                    statement.execute();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "product was not registered {}", e.getErrorCode());
            throw new DaoException();
        }
        return true;
    }
}
