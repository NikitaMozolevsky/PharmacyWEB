package by.mozolevskij.pharmacy.command.impl.product;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.impl.user.ShowUserListCommand;
import by.mozolevskij.pharmacy.dao.impl.ProductDaoImpl;
import by.mozolevskij.pharmacy.entity.prescription_request.PrescriptionRequestStatus;
import by.mozolevskij.pharmacy.entity.product.NeedPrescription;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.APPROVED;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.RESPONSE;
import static by.mozolevskij.pharmacy.command.attribute.PagePath.ADD_TO_CART_JSP;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.*;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.ROLE;
import static by.mozolevskij.pharmacy.entity.product.NeedPrescription.TRUE;

public class ChooseProductCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute(PRODUCT_ID, request.getParameter(PRODUCT_ID));
        String productId = request.getParameter(PRODUCT_ID);
        request.getSession().setAttribute(PRICE, request.getParameter(PRICE));
        request.setAttribute(ROLE, request.getParameter(ROLE));
        Optional<String> prescriptionRequestStatusString = Optional.ofNullable(request.getParameter(RESPONSE));
        Optional<String> needPrescriptionString = Optional.ofNullable(request.getParameter(NEED_PRESCRIPTION));
        NeedPrescription needPrescription;
        try {
            needPrescription = ProductDaoImpl.getInstance().needPrescriptionDao(productId);
            PrescriptionRequestStatus prescriptionRequestStatus;
            boolean prescriptionRequestStatusApproved = false;
            if (prescriptionRequestStatusString.isPresent()) {
                prescriptionRequestStatus = PrescriptionRequestStatus
                        .valueOf(request.getParameter(RESPONSE));
                prescriptionRequestStatusApproved = prescriptionRequestStatus.equals
                        (PrescriptionRequestStatus.APPROVED);
            }
            if (needPrescriptionString.isPresent()) {
                needPrescription = NeedPrescription.valueOf
                        (request.getParameter(NEED_PRESCRIPTION));
            }
            if (needPrescription.equals(TRUE) && !prescriptionRequestStatusApproved) {
                return new ShowUserListCommand().execute(request);
            }
            return new Router(ADD_TO_CART_JSP);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
    }
}
