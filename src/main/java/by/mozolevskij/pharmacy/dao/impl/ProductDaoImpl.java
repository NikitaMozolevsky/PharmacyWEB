package by.mozolevskij.pharmacy.dao.impl;

import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;
import by.mozolevskij.pharmacy.entity.product.DrugType;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.pool.ConnectionPool;
import by.mozolevskij.pharmacy.dao.BaseDao;
import by.mozolevskij.pharmacy.entity.AbstractEntity;
import by.mozolevskij.pharmacy.entity.product.Product;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.*;
import static by.mozolevskij.pharmacy.dao.request.ProductDaoRequest.*;

public class ProductDaoImpl extends BaseDao {

    static Logger logger = LogManager.getLogger();

    private static ProductDaoImpl productDao = new ProductDaoImpl();

    private ProductDaoImpl() {}

    public static ProductDaoImpl getInstance() {
        return productDao;
    }

    public void registerDao(Product productData) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_REQUEST)) {

            statement.setString(1, productData.getProductName());
            statement.setString(2, productData.getDetails());
            statement.setString(3, String.valueOf(productData.getPrice()));
            statement.setString(4, String.valueOf(productData.getType()));
            statement.setString(5, productData.getPhoto());
            statement.setInt(6, productData.getQuantity());
            statement.setBoolean(7, productData.isNeedPrescription());

            statement.execute();
            logger.log(Level.INFO, "product was added successful");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "product was not registered");
            throw new DaoException();
        }
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
                product.setPhoto(resultSet.getString(PHOTO));
                product.setQuantity(resultSet.getInt(GOODS_QUANTITY));
                product.setNeedPrescription(resultSet.getBoolean(NEED_PRESCRIPTION));
                products.add(product);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in finding all products", e);
        }
        return products;
    }

    public void addProductQuantityDao(Product product) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement getProductQuantityStatement = connection.prepareStatement
                     (GET_PRODUCT_QUANTITY);
             PreparedStatement setProductQuantityStatement = connection.prepareStatement
                     (UPDATE_GOODS_QUANTITY)) {
            String productId = String.valueOf(product.getProductId());
                 getProductQuantityStatement.setString(1, productId);
                 try(ResultSet resultSet = getProductQuantityStatement.executeQuery()) {
                     if (resultSet.next()) {
                         int currentProductQuantity = resultSet.getInt(1);
                         int finalProductQuantity = currentProductQuantity + product.getQuantity();
                         setProductQuantityStatement.setInt(1, finalProductQuantity);
                         setProductQuantityStatement.setString(2, productId);
                         setProductQuantityStatement.executeUpdate();
                     }
                 }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
    }



    @Override
    public AbstractEntity update(AbstractEntity abstractEntity) throws DaoException {
        return null;
    }
}
