package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.entity.prescription_request.PrescriptionRequestStatus;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.mozolevskij.pharmacy.command.attribute.Message.APPROVED_PRESCRIPTION_MSG;
import static by.mozolevskij.pharmacy.command.attribute.Message.REJECTED_PRESCRIPTION_MSG;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*;

public class SendResponseCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        PrescriptionRequestStatus prescriptionRequestStatus = PrescriptionRequestStatus
                .valueOf(request.getParameter(RESPONSE));
        int prescriptionRequestId = Integer.parseInt(request.getParameter
                (PRESCRIPTION_REQUEST_ID));
        try {
            orderDao.updateRequestPrescriptionStatus
                    (prescriptionRequestStatus, prescriptionRequestId);
            switch (prescriptionRequestStatus) {
                case APPROVED -> request.setAttribute(RESPONSE, APPROVED_PRESCRIPTION_MSG);
                case REJECTED -> request.setAttribute(RESPONSE, REJECTED_PRESCRIPTION_MSG);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        return new PrescriptionRequestListPageCommand().execute(request);
    }
}
