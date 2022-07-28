package by.mozolevskij.pharmacy.dao.impl;

import by.mozolevskij.pharmacy.dao.request.OrderDaoRequest;
import by.mozolevskij.pharmacy.entity.order.Order;
import by.mozolevskij.pharmacy.entity.order.OrderStatus;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.pool.ConnectionPool;
import by.mozolevskij.pharmacy.dao.BaseDao;
import by.mozolevskij.pharmacy.entity.AbstractEntity;
import by.mozolevskij.pharmacy.entity.user.User;
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

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.CLOSED;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.INITIAL_GOODS_QUANTITY;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.INITIAL_MONEY_AMOUNT;
import static by.mozolevskij.pharmacy.dao.request.OrderDaoRequest.*;


public class OrderDaoImpl extends BaseDao {

    static Logger logger = LogManager.getLogger();

    private static OrderDaoImpl orderDao = new OrderDaoImpl();

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        return orderDao;
    }

    @Override
    public boolean delete(AbstractEntity abstractEntity) throws DaoException {
        return false;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }

    @Override
    public AbstractEntity update(AbstractEntity abstractEntity) throws DaoException {
        return null;
    }

    public Optional<String> addOrderDao(Order order) throws DaoException {
        String userId = String.valueOf(order.getUserId());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(OrderDaoRequest.ADD_NEW_ORDER);
             PreparedStatement getOrderIdByUserIdStatement = connection.prepareStatement
                     (OrderDaoRequest.GET_ORDER_ID_BY_USER_ID)) {

            statement.setString(1, userId);
            statement.setString(2, String.valueOf(order.getOrderStatus()));
            statement.setString(3, String.valueOf(order.getDateOpen()));
            statement.setString(4, String.valueOf(order.getFullCost()));

            statement.execute();
            getOrderIdByUserIdStatement.setString(1, userId);
            try (ResultSet resultSet = getOrderIdByUserIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    String orderId = resultSet.getString(1);
                    return Optional.of(orderId);
                }
            }
            logger.log(Level.INFO, "order was added successful");
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return Optional.empty();
    }

    public Optional<String> getOrderIdByUserIdDao(String userId) throws DaoException {
        Optional<String> orderId = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (OrderDaoRequest.GET_ORDER_ID_BY_USER_ID)) {
            statement.setString(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    orderId = Optional.of(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return orderId;
    }

    public boolean isOrderForUserIsNotExistDao(String userId) throws DaoException {
        boolean orderIsNotExist;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement orderStatusStatement = connection.prepareStatement
                     (GET_ORDER_ID_AND_STATUS_BY_USER_ID)) {
            orderStatusStatement.setString(1, userId);
            try (ResultSet resultSet = orderStatusStatement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<String> orderIdOptional = Optional.of
                            (resultSet.getString(1));
                    Optional<String> orderStatusOptional = Optional.of
                            (resultSet.getString(2));
                    orderIsNotExist = orderIdOptional.get().isEmpty();
                    if (orderIsNotExist) {
                        return true;
                    }
                    orderIsNotExist = orderStatusOptional.get().equals(CLOSED);
                    if (!orderIsNotExist) {
                        return false;
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
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

    public Double topUpAccountDao(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement getMoneyAmountStatement = connection.prepareStatement
                     (OrderDaoRequest.GET_MONEY_AMOUNT_BY_USER_ID);
             PreparedStatement setMoneyAmountStatement = connection.prepareStatement
                     (OrderDaoRequest.UPDATE_MONEY_AMOUNT_BY_USER_ID)) {
            String userId = String.valueOf(user.getUserId());
            getMoneyAmountStatement.setString(1, userId);
            try (ResultSet resultSet = getMoneyAmountStatement.executeQuery()) {
                if (resultSet.next()) {
                    double resultSetDouble = resultSet.getDouble(1);
                    Double finalMonetaryAmount = user.getMoneyAmount() + resultSetDouble;
                    setMoneyAmountStatement.setString(1, String.valueOf
                            (finalMonetaryAmount));
                    setMoneyAmountStatement.setString(2, userId);
                    setMoneyAmountStatement.executeUpdate();
                    logger.log(Level.INFO, "account was top up");
                    return finalMonetaryAmount;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return INITIAL_MONEY_AMOUNT;
    }

    public void payForOrderDao(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (OrderDaoRequest.CLOSE_ORDER)) {
            statement.setString(1, order.getAddress());
            statement.setString(2, String.valueOf(order.getOrderStatus()));
            statement.setString(3, String.valueOf(order.getDateClose()));
            statement.setInt(4, order.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
    }

    public Optional<List<String>> getSufficientGoodsQuantity(String orderId) throws DaoException {
        List<String> insufficientProducts = new ArrayList<>();
        int sufficientGoodsQuantity;
        boolean balanceGoodsQuantityPositive = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement getOrderProductInfoStatement = connection.prepareStatement
                     (GET_ORDER_PRODUCT_QUANTITIES_AND_ORDER_PRODUCT_IDES_BY_ORDER_ID);
             PreparedStatement getProductQuantityStatement = connection.prepareStatement
                     (GET_PRODUCT_QUANTITY_AND_PRODUCT_NAME_BY_PRODUCT_ID);
             PreparedStatement setProductQuantityStatement = connection.prepareStatement
                     (SET_PRODUCT_QUANTITY_BY_PRODUCT_ID)) {
            getOrderProductInfoStatement.setString(1, orderId);
            try (ResultSet resultSet = getOrderProductInfoStatement.executeQuery()) {
                while (resultSet.next()) {
                    String productId = resultSet.getString(1);
                    int orderProductQuantity = resultSet.getInt(2);
                    getProductQuantityStatement.setString(1, productId);
                    try (ResultSet secondResultSet = getProductQuantityStatement.executeQuery()) {
                        if (secondResultSet.next()) {
                            int productQuantity = secondResultSet.getInt(1);
                            String productName = secondResultSet.getString(2);
                            sufficientGoodsQuantity = productQuantity - orderProductQuantity;
                            if (sufficientGoodsQuantity >= INITIAL_GOODS_QUANTITY) { //количества товра достоточно
                                setProductQuantityStatement.setInt(1, sufficientGoodsQuantity);
                                setProductQuantityStatement.setString(2, productId);
                                setProductQuantityStatement.executeUpdate();
                            } else {
                                insufficientProducts.add(productName);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return Optional.of(insufficientProducts);
    }

    public Double increaseFullCostDao(String orderId, String orderProductCost) throws DaoException {
        double fullCost = INITIAL_MONEY_AMOUNT;
        double orderProductCostDouble = Double.parseDouble(orderProductCost);
        double currentFullCost = INITIAL_MONEY_AMOUNT;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement addOrderProductCostInFullCostStatement = connection.prepareStatement
                     (OrderDaoRequest.SET_FULL_COST_BY_ORDER_ID);
             PreparedStatement getOrderFullCostStatement = connection.prepareStatement
                     (OrderDaoRequest.GET_FULL_COST_BY_ORDER_ID)) {
            getOrderFullCostStatement.setString(1, orderId);
            try (ResultSet resultSet = getOrderFullCostStatement.executeQuery()) {
                if (resultSet.next()) {
                    currentFullCost = resultSet.getDouble(1);
                }
            }
            fullCost = orderProductCostDouble + currentFullCost;
            addOrderProductCostInFullCostStatement.setDouble(1, fullCost);
            addOrderProductCostInFullCostStatement.setString(2, orderId);
            addOrderProductCostInFullCostStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return fullCost;
    }

    public OrderStatus getOrderStatusDao(String orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement getStatusByOrderIdStatement = connection.prepareStatement
                     (GET_STATUS_BY_ORDER_ID)) {
            getStatusByOrderIdStatement.setString(1, orderId);
            try (ResultSet resultSetFirst = getStatusByOrderIdStatement.executeQuery()) {
                if (resultSetFirst.next()) {
                    return OrderStatus.valueOf(resultSetFirst.getString(1));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return OrderStatus.CLOSED;
    }

    public Double getFullCostDao(String orderId) throws DaoException {
        double orderFullCost = INITIAL_MONEY_AMOUNT;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement getFullCostByOrderIdStatement = connection.prepareStatement
                     (GET_FULL_COST_BY_ORDER_ID);
             PreparedStatement setFullCostByOrderIdStatement = connection.prepareStatement
                     (SET_FULL_COST_BY_ORDER_ID)) {
            if (getOrderStatusDao(orderId) == OrderStatus.CLOSED) {
                return orderFullCost;
            }
            getFullCostByOrderIdStatement.setString(1, orderId);
            try (ResultSet resultSet = getFullCostByOrderIdStatement.executeQuery()) {
                while (resultSet.next()) {
                    orderFullCost += resultSet.getDouble(1);
                }
                setFullCostByOrderIdStatement.setDouble(1, orderFullCost);
                setFullCostByOrderIdStatement.setString(2, orderId);
                setFullCostByOrderIdStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return orderFullCost;
    }

    public void setFullCostDao(String orderId) throws DaoException {
        Double orderFullCost = INITIAL_MONEY_AMOUNT;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement setFullCostByOrderIdStatement = connection.prepareStatement
                     (OrderDaoRequest.SET_FULL_COST_BY_ORDER_ID)) {


        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
    }
}
