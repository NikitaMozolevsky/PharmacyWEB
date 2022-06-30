package com.example.demo.dao.impl;

import com.example.demo.dao.BaseDao;
import com.example.demo.dao.request.OrderDaoRequest;
import com.example.demo.entity.AbstractEntity;
import com.example.demo.entity.order.Order;
import com.example.demo.exception.DaoException;
import com.example.demo.pool.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.example.demo.command.attribute.OrderAttribute.*;
import static com.example.demo.command.attribute.UserAttribute.USER_ID;
import static com.example.demo.dao.request.OrderDaoRequest.ADD_NEW_ORDER;
import static com.example.demo.dao.request.OrderDaoRequest.GET_ORDER_ID_BY_USER_ID;


public class OrderDaoImpl extends BaseDao {

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

    public boolean addOrderDao(Order order) throws DaoException, SQLException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_ORDER);
             PreparedStatement getOrderIdStatement = connection.prepareStatement
                     (GET_ORDER_ID_BY_USER_ID)) {

            statement.setString(1, String.valueOf(order.getUserId()));
            statement.setString(2, String.valueOf(order.getOrderStatus()));
            statement.setString(3, String.valueOf(order.getDateOpen()));
            statement.setString(4, String.valueOf(order.getFullCost()));

            statement.execute();
            logger.log(Level.INFO, "order was added successful");
            return true;
        }

    }

    public Optional<String> getOrderID(HttpServletRequest request) throws DaoException {
        Optional<String> orderId = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ORDER_ID_BY_USER_ID)) {
                 statement.setString(1, (String) request.getSession().getAttribute(USER_ID));
                 try(ResultSet resultSet = statement.executeQuery()) {
                     if(resultSet.next()) {
                         orderId = Optional.of(resultSet.getString(1));
                     }
                 }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return orderId;
    }

    public boolean isOrderForUserIsNotExist(String userId) throws DaoException {
        boolean orderIsNotExist = true;
        /*List<String> strings = new ArrayList<>();*/
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement orderExistStatement = connection.prepareStatement
                     (OrderDaoRequest.GET_ORDER_ID_BY_USER_ID);
             PreparedStatement orderStatusStatement = connection.prepareStatement
                     (OrderDaoRequest.GET_ORDER_STATUS_BY_USER_ID)) {
            orderExistStatement.setString(1, userId);
            orderStatusStatement.setString(1, userId);
            try(ResultSet resultSet = orderExistStatement.executeQuery()) {
                if (resultSet.next()) {
                    Optional<String> orderIdByUserOptional = Optional.of
                            (resultSet.getString(1));
                    orderIsNotExist = orderIdByUserOptional.isEmpty();
                }
            }
            try(ResultSet resultSet = orderStatusStatement.executeQuery()) {
                if (resultSet.next()) {
                    String orderStatus = resultSet.getString(1);
                    orderIsNotExist = orderStatus.equals(CLOSED);
                    /*strings.add(orderStatus);*/
                }
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return orderIsNotExist;
    }

    public void getOrderById(String userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement orderExistStatement = connection.prepareStatement
                     (OrderDaoRequest.GET_ORDER_ID_BY_USER_ID)) {

        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
    }
}
