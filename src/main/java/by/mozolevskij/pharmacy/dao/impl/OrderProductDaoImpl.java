package by.mozolevskij.pharmacy.dao.impl;

import by.mozolevskij.pharmacy.dao.request.OrderDaoRequest;
import by.mozolevskij.pharmacy.dao.request.ProductDaoRequest;
import by.mozolevskij.pharmacy.entity.order_product.OrderProduct;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.pool.ConnectionPool;
import by.mozolevskij.pharmacy.dao.BaseDao;
import by.mozolevskij.pharmacy.entity.AbstractEntity;
import by.mozolevskij.pharmacy.util.ImageConverter;
import by.mozolevskij.pharmacy.util.VolumeToNumber;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.*;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.INITIAL_MONEY_AMOUNT;
import static by.mozolevskij.pharmacy.dao.request.OrderDaoRequest.REMOVE_ORDER_PRODUCT;
import static by.mozolevskij.pharmacy.dao.request.OrderDaoRequest.SET_ORDER_STATUS_IN_PROCESS;
import static by.mozolevskij.pharmacy.entity.order.OrderStatus.IN_PROCESS;


public class OrderProductDaoImpl extends BaseDao {

    private static OrderProductDaoImpl orderProductDao = new OrderProductDaoImpl();

    private OrderProductDaoImpl() {
    }

    public static OrderProductDaoImpl getInstance() {
        return orderProductDao;
    }

    static Logger logger = LogManager.getLogger();

    public boolean delete(String orderProductId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (REMOVE_ORDER_PRODUCT)) {
            statement.setString(1, orderProductId);
            statement.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return false;
    }

    @Override
    public boolean delete(AbstractEntity abstractEntity) throws DaoException {
        throw new UnsupportedOperationException
                ("delete is unsupported operation");
    }

    @Override
    public List<OrderProduct> findAll() throws DaoException {
        throw new UnsupportedOperationException
                ("find all is unsupported operation");
    }

    @Override
    public AbstractEntity update(AbstractEntity abstractEntity) throws DaoException {
        throw new UnsupportedOperationException
                ("update is unsupported operation");
    }

    public List<OrderProduct> findAllByOrderIdDao(String currentOrderId) throws DaoException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (OrderDaoRequest.GET_ORDER_PRODUCT_INFO_AND_PRODUCT_INFO_BY_ORDER_ID)) {
            statement.setString(1, currentOrderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OrderProduct orderProduct = new OrderProduct();
                    /*orderProduct.setOrderProductId(Integer.parseInt(resultSet.getString
                            (ORDER_PRODUCT_ID)));*/
                    orderProduct.setOrderProductId(Integer.parseInt(resultSet.getString
                            (ORDER_PRODUCT_ID)));
                    orderProduct.setOrderId(Integer.parseInt(resultSet.getString(ORDER_ID)));
                    orderProduct.setProductId(Integer.parseInt(resultSet.getString(PRODUCT_ID)));
                    orderProduct.setQuantity(Integer.parseInt(resultSet.getString(QUANTITY)));
                    orderProduct.setVolume(resultSet.getString(VOLUME));
                    orderProduct.setOrderProductPrice(Double.parseDouble(resultSet.getString
                            (ORDER_PRODUCT_PRICE)));
                    orderProduct.setProductName(resultSet.getString(PRODUCT_NAME));
                    Blob blobPhoto = resultSet.getBlob(PHOTO);
                    if (blobPhoto != null) {
                        byte[] imageContent = blobPhoto.getBytes(1, (int) blobPhoto.length());
                        String encodeBase64 = ImageConverter.imageToString(imageContent);
                        //TODO scale image???
                        orderProduct.setPhoto(encodeBase64);
                    }
                    orderProducts.add(orderProduct);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in finding all orderProducts {}",
                    e.getErrorCode());
            throw new DaoException();
        }
        return orderProducts;
    }

    public Double addOrderProductDao(OrderProduct orderProduct) throws DaoException {
        Double orderProductCost = INITIAL_MONEY_AMOUNT;
        try (Connection connectionFirst = ConnectionPool.getInstance().getConnection();
             Connection connectionSecond = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connectionFirst.prepareStatement(OrderDaoRequest.ADD_ORDER_PRODUCT);
             PreparedStatement getOrderIdStatement = connectionFirst.prepareStatement
                     (OrderDaoRequest.GET_ORDER_ID_BY_USER_ID);
             PreparedStatement getProductPriceByProductIdStatement = connectionSecond.prepareStatement
                     (ProductDaoRequest.GET_PRODUCT_PRICE_BY_ID)) {
            VolumeToNumber volumeToNumber = new VolumeToNumber();
            String userId = String.valueOf(orderProduct.getUserId());
            getOrderIdStatement.setString(1, userId);
            try (ResultSet resultSet = getOrderIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    String orderId = resultSet.getString(1);
                    String productId = String.valueOf(orderProduct.getProductId());
                    int orderProductQuantity = orderProduct.getQuantity();
                    String orderProductVolume = orderProduct.getVolume();
                    getProductPriceByProductIdStatement.setString(1, productId);
                    statement.setString(1, orderId);
                    statement.setString(2, productId);
                    statement.setString(3, String.valueOf(orderProductQuantity));
                    statement.setString(4, orderProductVolume);
                    try (ResultSet secondResultSet = getProductPriceByProductIdStatement
                            .executeQuery()) {
                        if (secondResultSet.next()) {
                            String productPrice = secondResultSet.getString(1);
                            int orderProductVolumeInt = volumeToNumber.convert(orderProductVolume);
                            orderProductCost = Double.parseDouble(productPrice) *
                                    orderProductVolumeInt * orderProductQuantity;
                            statement.setString(5, String.valueOf(orderProductCost));
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
        return orderProductCost;
    }

    public void setOrderStatusInProcessDao(OrderProduct orderProduct) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_ORDER_STATUS_IN_PROCESS);
             PreparedStatement getOrderIdStatement = connection.prepareStatement
                     (OrderDaoRequest.GET_ORDER_ID_BY_USER_ID)) {
            String userId = String.valueOf(orderProduct.getUserId());
            getOrderIdStatement.setString(1, userId);
            try (ResultSet resultSet = getOrderIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    String orderId = resultSet.getString(1);
                    statement.setString(1, String.valueOf(IN_PROCESS));
                    statement.setString(2, orderId);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "product was not registered {}", e.getErrorCode());
            throw new DaoException();
        }
    }
}
