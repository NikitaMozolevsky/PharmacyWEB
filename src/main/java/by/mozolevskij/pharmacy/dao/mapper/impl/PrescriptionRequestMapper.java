package by.mozolevskij.pharmacy.dao.mapper.impl;

import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;
import by.mozolevskij.pharmacy.dao.mapper.Mapper;
import by.mozolevskij.pharmacy.entity.prescription_request.PrescriptionRequest;
import by.mozolevskij.pharmacy.entity.prescription_request.PrescriptionRequestStatus;
import by.mozolevskij.pharmacy.entity.product.DrugType;
import by.mozolevskij.pharmacy.entity.product.Product;
import by.mozolevskij.pharmacy.exception.DaoException;
import org.apache.logging.log4j.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PRESCRIPTION_REQUEST_ID;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.RESPONSE;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCT_NAME;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.CLIENT_ID;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.DOCTOR_ID;

public class PrescriptionRequestMapper implements Mapper<PrescriptionRequest> {
    @Override
    public Optional<PrescriptionRequest> mapEntity(ResultSet resultSet) {
        PrescriptionRequest prescriptionRequest = new PrescriptionRequest();
        Optional<PrescriptionRequest> optionalPrescriptionRequest;
        try {
            prescriptionRequest.setPrescriptionRequestId(resultSet.getInt(PRESCRIPTION_REQUEST_ID));
            prescriptionRequest.setClientId(resultSet.getInt(CLIENT_ID));
            prescriptionRequest.setDoctorId(resultSet.getInt(DOCTOR_ID));
            prescriptionRequest.setProductName(resultSet.getString(PRODUCT_NAME));
            prescriptionRequest.setPrescriptionRequestStatus(PrescriptionRequestStatus.valueOf(resultSet.getString(RESPONSE)));
            prescriptionRequest.setProductId(resultSet.getInt(ProductAttribute.PRODUCT_ID));

            optionalPrescriptionRequest = Optional.of(prescriptionRequest);
        } catch (SQLException e) {
            logger.log(Level.INFO, "product wasn't mapped in ProductMapper. {}", e.getMessage());
            optionalPrescriptionRequest = Optional.empty();
        }
        return optionalPrescriptionRequest;
    }
}
