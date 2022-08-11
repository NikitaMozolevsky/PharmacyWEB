package by.mozolevskij.pharmacy.entity.prescription_request;

import by.mozolevskij.pharmacy.dao.BaseDao;
import by.mozolevskij.pharmacy.entity.AbstractEntity;
import by.mozolevskij.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class PrescriptionRequest extends AbstractEntity {

    private int prescriptionRequestId;
    private int clientId;
    private int doctorId;
    private int productId;
    private String productName;
    private PrescriptionRequestStatus prescriptionRequestStatus;

    public PrescriptionRequest() {
    }

    public PrescriptionRequest(int prescriptionRequestId, int clientId, int doctorId,
                               int productId, String productName,
                               PrescriptionRequestStatus prescriptionRequestStatus) {
        this.prescriptionRequestId = prescriptionRequestId;
        this.clientId = clientId;
        this.doctorId = doctorId;
        this.productId = productId;
        this.productName = productName;
        this.prescriptionRequestStatus = prescriptionRequestStatus;
    }

    public int getPrescriptionRequestId() {
        return prescriptionRequestId;
    }

    public void setPrescriptionRequestId(int prescriptionRequestId) {
        this.prescriptionRequestId = prescriptionRequestId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public PrescriptionRequestStatus getPrescriptionRequestStatus() {
        return prescriptionRequestStatus;
    }

    public void setPrescriptionRequestStatus(PrescriptionRequestStatus prescriptionRequestStatus) {
        this.prescriptionRequestStatus = prescriptionRequestStatus;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrescriptionRequest that = (PrescriptionRequest) o;

        if (prescriptionRequestId != that.prescriptionRequestId) return false;
        if (clientId != that.clientId) return false;
        if (doctorId != that.doctorId) return false;
        if (productId != that.productId) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        return prescriptionRequestStatus == that.prescriptionRequestStatus;
    }

    @Override
    public int hashCode() {
        int result = prescriptionRequestId;
        result = 31 * result + clientId;
        result = 31 * result + doctorId;
        result = 31 * result + productId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (prescriptionRequestStatus != null ? prescriptionRequestStatus.hashCode() : 0);
        return result;
    }
}
