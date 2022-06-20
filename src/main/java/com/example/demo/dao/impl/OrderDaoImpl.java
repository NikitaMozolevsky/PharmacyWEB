package com.example.demo.dao.impl;

import com.example.demo.dao.BaseDao;
import com.example.demo.entity.AbstractEntity;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order_product.OrderProduct;
import com.example.demo.entity.product.Product;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.pool.ConnectionPool;
import com.mysql.cj.Session;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.demo.dao.request.OrderDaoRequest.ADD_NEW_ORDER;
import static com.example.demo.dao.request.OrderDaoRequest.GET_LAST_ORDER;
import static com.example.demo.dao.request.ProductDaoRequest.ADD_PRODUCT_REQUEST;


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
             PreparedStatement getOrderIdStatement = connection.prepareStatement(GET_LAST_ORDER)) {

            statement.setString(1, String.valueOf(order.getUserId()));
            statement.setString(2, String.valueOf(order.getOrderStatus()));
            statement.setString(3, String.valueOf(order.getDateOpen()));
            statement.setString(4, String.valueOf(order.getFullCost()));

            statement.execute();

            try (ResultSet resultSet = getOrderIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    String orderId = resultSet.getString(1);
                }

                logger.log(Level.INFO, "order was added successful");
            } catch (SQLException e) {
                logger.log(Level.ERROR, "put order in DB Exception", e);
                throw new DaoException();
            }
            return true;
        }

    }
}
