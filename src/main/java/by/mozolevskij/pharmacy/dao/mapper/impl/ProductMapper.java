package by.mozolevskij.pharmacy.dao.mapper.impl;

import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;
import by.mozolevskij.pharmacy.dao.mapper.Mapper;
import by.mozolevskij.pharmacy.entity.product.DrugType;
import by.mozolevskij.pharmacy.entity.product.Product;
import org.apache.logging.log4j.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductMapper implements Mapper<Product> {


    @Override
    public Optional<Product> mapEntity(ResultSet resultSet) {
        Product product = new Product();
        Optional<Product> optionalProduct = Optional.empty();
        List<String> resultSetList = new ArrayList<>();
        try {
            product.setProductId(resultSet.getInt(ProductAttribute.PRODUCT_ID));
            product.setProductName(resultSet.getString(ProductAttribute.PRODUCT_NAME));
            product.setDetails(resultSet.getString(ProductAttribute.DETAILS));
            product.setPrice(resultSet.getDouble(ProductAttribute.PRICE));
            product.setType(DrugType.valueOf(resultSet.getString(ProductAttribute.TYPE)));
            product.setVolume(resultSet.getString(ProductAttribute.VOLUME));
            product.setPhoto(resultSet.getString(ProductAttribute.PHOTO));
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
