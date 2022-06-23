package com.example.demo.dao.impl;

import com.example.demo.dao.BaseDao;
import com.example.demo.dao.request.UserDaoRequest;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.command.constant.OrderAttribute.*;
import static com.example.demo.command.constant.UserAttribute.USER_ID;
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
             PreparedStatement getOrderIdStatement = connection.prepareStatement(GET_ORDER_ID_BY_USER_ID)) {

            statement.setString(1, String.valueOf(order.getUserId()));
            statement.setString(2, String.valueOf(order.getOrderStatus()));
            statement.setString(3, String.valueOf(order.getDateOpen()));
            statement.setString(4, String.valueOf(order.getFullCost()));

            statement.execute();
            logger.log(Level.INFO, "order was added successful");
            return true;
        }

    }

    public Optional<String> getOrderID(HttpServletRequest request) throws SQLException {
        Optional<String> orderId = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ORDER_ID_BY_USER_ID)) {
                 statement.setString(1, (String) request.getSession().getAttribute(USER_ID));
                 try(ResultSet resultSet = statement.executeQuery()) {
                     if(resultSet.next()) {
                         orderId = Optional.of(resultSet.getString(1));
                     }
                 }
        }
        return orderId;
    }

    public boolean isOrderForUserAlreadyExist(String userId) throws SQLException {
        boolean isOrderExist = false;
        List<String> strings = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement orderExistStatement = connection.prepareStatement(UserDaoRequest.GET_ORDER_ID_BY_USER_ID);
             PreparedStatement orderStatusStatement = connection.prepareStatement(UserDaoRequest.GET_ORDER_STATUS_BY_USER_ID)) {
            orderExistStatement.setString(1, userId);
            orderStatusStatement.setString(1, userId);
            try(ResultSet resultSet = orderExistStatement.executeQuery()) {
                if (resultSet.next()) {
                    isOrderExist = resultSet.getString(1).isEmpty();
                }
            }
            try(ResultSet resultSet = orderStatusStatement.executeQuery()) {
                if (resultSet.next()) {
                    isOrderExist = resultSet.getString(1).equals(CLOSED);
                    strings.add(resultSet.getString(1));
                }
            }
        }
        return isOrderExist;
    }
}
