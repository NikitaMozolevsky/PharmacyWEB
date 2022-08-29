package by.mozolevskij.pharmacy.dao.impl;

import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;
import by.mozolevskij.pharmacy.entity.product.DrugType;
import by.mozolevskij.pharmacy.entity.product.NeedPrescription;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.pool.ConnectionPool;
import by.mozolevskij.pharmacy.dao.BaseDao;
import by.mozolevskij.pharmacy.entity.AbstractEntity;
import by.mozolevskij.pharmacy.entity.product.Product;
import by.mozolevskij.pharmacy.util.ImageConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
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

    public void registerDao(Product productData, byte[] photo) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_REQUEST)) {

            InputStream photoStream = new ByteArrayInputStream(photo);

            statement.setString(1, productData.getProductName());
            statement.setString(2, productData.getDetails());
            statement.setString(3, String.valueOf(productData.getPrice()));
            statement.setString(4, String.valueOf(productData.getType()));
            statement.setBinaryStream(5, photoStream);
            statement.setInt(6, productData.getQuantity());
            statement.setString(7, String.valueOf(productData.getNeedPrescription()));

            statement.execute();
            logger.log(Level.INFO, "product was added successful");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "product was not registered");
            throw new DaoException();
        }
    }

    @Override
    public boolean delete(AbstractEntity abstractEntity) throws DaoException {
        throw new UnsupportedOperationException
                ("delete is unsupported operation");
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
                product.setQuantity(resultSet.getInt(GOODS_QUANTITY));
                product.setNeedPrescription(NeedPrescription.valueOf(resultSet.getString(NEED_PRESCRIPTION)));
                Blob blobPhoto = resultSet.getBlob(PHOTO);
                if (blobPhoto != null) {
                    byte[] imageContent = blobPhoto.getBytes(1, (int) blobPhoto.length());
                    String encodeBase64 = ImageConverter.imageToString(imageContent);
                    //TODO scale image???
                    product.setPhoto(encodeBase64);
                }
                products.add(product);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in finding all products", e);
        }
        return products;
    }

    @Override
    public AbstractEntity update(AbstractEntity abstractEntity) throws DaoException {
        throw new UnsupportedOperationException
                ("update is unsupported operation");
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

    public NeedPrescription needPrescriptionDao(String productId) throws DaoException {
        NeedPrescription needPrescription = NeedPrescription.FALSE;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement getNeedPrescriptionStatement = connection.prepareStatement
                     (GET_NEED_PRESCRIPTION)) {
            getNeedPrescriptionStatement.setString(1, String.valueOf(productId));
            try(ResultSet resultSet = getNeedPrescriptionStatement.executeQuery()) {
                if (resultSet.next()) {
                    needPrescription = NeedPrescription.valueOf(resultSet.getString(1));
                }
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return needPrescription;
    }

}
