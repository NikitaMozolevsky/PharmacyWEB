package com.example.demo.dao.mapper.impl;

import com.example.demo.dao.mapper.Mapper;
import com.example.demo.entity.product.DrugType;
import com.example.demo.entity.product.Product;
import org.apache.logging.log4j.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.example.demo.command.attribute.ProductAttribute.*;

public class ProductMapper implements Mapper<Product> {


    @Override
    public Optional<Product> mapEntity(ResultSet resultSet) {
        Product product = new Product();
        Optional<Product> optionalProduct = Optional.empty();
        try {
            product.setProductId(resultSet.getInt(PRODUCT_ID));
            product.setProductName(resultSet.getString(PRODUCT_NAME));
            product.setDetails(resultSet.getString(DETAILS));
            product.setPrice(resultSet.getDouble(PRICE));
            product.setType(DrugType.valueOf(resultSet.getString(TYPE)));
            product.setVolume(resultSet.getString(VOLUME));
            product.setPhoto(resultSet.getString(PHOTO));
            /*Blob blobPhoto = resultSet.getBlob(PHOTO);
            if (blobPhoto != null) {
                byte[] imageContent = blobPhoto.getBytes(1, (int) blobPhoto.length());
                String encodeBase64 = ImageConverter.imageToString(imageContent);
                product.setPhotoString(encodeBase64);
            }*/

            optionalProduct = Optional.of(product);
        } catch (SQLException e) {
            logger.log(Level.INFO, "product wasn't mapped in ProductMapper. {}", e.getMessage());
            optionalProduct = Optional.empty();
        }
        return optionalProduct;
    }
}
