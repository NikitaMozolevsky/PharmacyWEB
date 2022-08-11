package by.mozolevskij.pharmacy.service.impl;

import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.entity.prescription_request.PrescriptionRequest;
import by.mozolevskij.pharmacy.entity.prescription_request.PrescriptionRequestStatus;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.PrescriptionRequestService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.RESPONSE;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCT_ID;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.CLIENT_ID;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.DOCTOR_ID;

public class PrescriptionRequestServiceImpl implements PrescriptionRequestService {

    static Logger logger = LogManager.getLogger();

    private PrescriptionRequestServiceImpl() {}

    private static final PrescriptionRequestServiceImpl prescriptionRequestService
            = new PrescriptionRequestServiceImpl();

    public static PrescriptionRequestServiceImpl getInstance() {
        return prescriptionRequestService;
    }

    @Override
    public void requestPrescriptionService(Map<String, String> prescriptionRequestData) throws ServiceException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        PrescriptionRequest prescriptionRequest = new PrescriptionRequest();
        prescriptionRequest.setClientId(Integer.parseInt
                (prescriptionRequestData.get(CLIENT_ID)));
        prescriptionRequest.setDoctorId(Integer.parseInt
                (prescriptionRequestData.get(DOCTOR_ID)));
        prescriptionRequest.setProductId(Integer.parseInt
                (prescriptionRequestData.get(PRODUCT_ID)));
        prescriptionRequest.setPrescriptionRequestStatus(PrescriptionRequestStatus.valueOf
                (prescriptionRequestData.get(RESPONSE)));
        try {
            orderDao.requestPrescriptionDao(prescriptionRequest);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new ServiceException();
        }
    }
}
