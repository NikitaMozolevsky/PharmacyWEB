package by.mozolevskij.pharmacy.dao.mapper.impl;

import by.mozolevskij.pharmacy.command.attribute.OrderAttribute;
import by.mozolevskij.pharmacy.command.attribute.UserAttribute;
import by.mozolevskij.pharmacy.dao.mapper.Mapper;
import by.mozolevskij.pharmacy.entity.order.Order;
import by.mozolevskij.pharmacy.entity.order.OrderStatus;
import by.mozolevskij.pharmacy.exception.DaoException;
import org.apache.logging.log4j.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

public class OrderMapper implements Mapper<Order> {
    @Override
    public Optional<Order> mapEntity(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        Optional<Order> optionalOrder;
        try {
            order.setOrderId(resultSet.getInt(OrderAttribute.ORDER_ID));
            order.setUserId(resultSet.getInt(UserAttribute.USER_ID));
            order.setAddress(resultSet.getString(OrderAttribute.ADDRESS));
            order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(OrderAttribute.STATUS)));
            order.setDateOpen(LocalDateTime.parse(resultSet.getString(OrderAttribute.DATE_OPEN)));
            order.setDateClose(LocalDateTime.parse(resultSet.getString(OrderAttribute.DATE_CLOSE)));
            order.setFullCost(resultSet.getDouble(OrderAttribute.FULL_COST));
            /*Blob blobPhoto = resultSet.getBlob(PHOTO);
            if (blobPhoto != null) {
                byte[] imageContent = blobPhoto.getBytes(1, (int) blobPhoto.length());
                String encodeBase64 = ImageConverter.imageToString(imageContent);
                order.setPhotoString(encodeBase64);
            }*/

            optionalOrder = Optional.of(order);
        } catch (SQLException e) {
            logger.log(Level.INFO, "order wasn't mapped in OrderMapper. {}", e.getMessage());
            throw new DaoException();
        }
        return optionalOrder;
    }
}
