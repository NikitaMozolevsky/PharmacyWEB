package by.mozolevskij.pharmacy.service.impl;

import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;
import by.mozolevskij.pharmacy.command.attribute.UserAttribute;
import by.mozolevskij.pharmacy.dao.impl.OrderProductDaoImpl;
import by.mozolevskij.pharmacy.entity.order_product.OrderProduct;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.OrderProductService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.INITIAL_MONEY_AMOUNT;

public class OrderProductServiceImpl implements OrderProductService {

    static Logger logger = LogManager.getLogger();

    private static OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();

    private OrderProductServiceImpl() {}

    public static OrderProductServiceImpl getInstance() {
        return orderProductService;
    }

    @Override
    public Double addOrderProductService(Map<String, String> orderProductInfo) throws ServiceException {
        OrderProduct orderProduct = new OrderProduct();//for example
        Double orderProductCost = INITIAL_MONEY_AMOUNT;
        orderProduct.setProductId(Integer.parseInt(orderProductInfo.get(ProductAttribute.PRODUCT_ID))); // TODO: 5/17/2022 добавить Имя Фамилию ?
        orderProduct.setUserId(Integer.parseInt(orderProductInfo.get(UserAttribute.USER_ID))); // TODO: 5/17/2022 добавить Имя Фамилию ?
        orderProduct.setQuantity(Integer.parseInt(orderProductInfo.get(ProductAttribute.GOODS_QUANTITY)));
        orderProduct.setVolume(orderProductInfo.get(ProductAttribute.VOLUME));

        OrderProductDaoImpl orderProductDao = OrderProductDaoImpl.getInstance();
        try {
            orderProductCost = orderProductDao.addOrderProductDao(orderProduct);
            orderProductDao.setOrderStatusInProcessDao(orderProduct);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e);
        }
        return orderProductCost;
    }

    @Override
    public void showAllOrderProduct() {

    }
}
