package by.mozolevskij.pharmacy.service.impl;

import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;
import by.mozolevskij.pharmacy.entity.product.DrugType;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.ProductService;
import by.mozolevskij.pharmacy.dao.impl.ProductDaoImpl;
import by.mozolevskij.pharmacy.entity.product.Product;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.mozolevskij.pharmacy.command.attribute.DefaultAttribute.TRUE;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.*;

public class ProductServiceImpl implements ProductService {

    static Logger logger = LogManager.getLogger();

    private static ProductServiceImpl productService = new ProductServiceImpl();

    private ProductServiceImpl() {}

    public static ProductServiceImpl getInstance() {
        return productService;
    }

    @Override
    public boolean addProduct(Map<String, String> newProductData) throws ServiceException {
        Product product = new Product();//for example
        product.setProductName(newProductData.get(ProductAttribute.PRODUCT_NAME)); // TODO: 5/17/2022 добавить Имя Фамилию ?
        product.setDetails(newProductData.get(ProductAttribute.DETAILS));
        product.setPrice(Double.parseDouble(newProductData.get(ProductAttribute.PRICE)));
        product.setType(DrugType.valueOf(newProductData.get(ProductAttribute.TYPE)));
        product.setPhoto(newProductData.get(ProductAttribute.PHOTO));
        product.setQuantity(Integer.parseInt(newProductData.get(GOODS_QUANTITY)));
        String needPrescription = newProductData.get(NEED_PRESCRIPTION);
        if (needPrescription.equals(TRUE)) {
            product.setNeedPrescription(true);
        }
        else {
            product.setNeedPrescription(false);
        }

        ProductDaoImpl productDao = ProductDaoImpl.getInstance();
        try {
            productDao.registerDao(product);
        }
        catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new ServiceException();
        }
        return true;
    }

    @Override
    public boolean addProductToCart(Map<String, String> orderProductInfo) throws ServiceException {
        Product product = new Product();//for example
        product.setProductName(orderProductInfo.get(ProductAttribute.PRODUCT_NAME)); // TODO: 5/17/2022 добавить Имя Фамилию ?
        product.setDetails(orderProductInfo.get(ProductAttribute.DETAILS));
        product.setPrice(Double.parseDouble(orderProductInfo.get(ProductAttribute.PRICE)));
        product.setType(DrugType.valueOf(orderProductInfo.get(ProductAttribute.TYPE)));
        product.setVolume(orderProductInfo.get(ProductAttribute.VOLUME));//for example
        product.setPhoto(orderProductInfo.get(ProductAttribute.PHOTO));//for example

        ProductDaoImpl productDao = ProductDaoImpl.getInstance();
        try {
            productDao.registerDao(product);
        } catch (DaoException e) {
            try {
                throw new ServiceException(e);
            } catch (ServiceException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public void addToProductQuantity(Map<String, String> productInfo) throws ServiceException {
        ProductDaoImpl productDao = ProductDaoImpl.getInstance();
        Product product = new Product();
        product.setQuantity(Integer.parseInt(productInfo.get(GOODS_QUANTITY)));
        product.setProductId(Integer.parseInt(productInfo.get(PRODUCT_ID)));
        try {
            productDao.addProductQuantityDao(product);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new ServiceException();
        }
    }

    @Override
    public boolean showAllProducts() throws ServiceException {
        return false;
    }

    @Override
    public boolean showProductByName() throws ServiceException {
        return false;
    }

    @Override
    public boolean showProductById() throws ServiceException {
        return false;
    }
}
