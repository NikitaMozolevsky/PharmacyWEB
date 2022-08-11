package by.mozolevskij.pharmacy.service;

import by.mozolevskij.pharmacy.exception.ServiceException;

import java.util.Map;

public interface PrescriptionRequestService {
    void requestPrescriptionService(Map<String, String> prescriptionRequestData)
            throws ServiceException;
}
