package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.DefaultCommand;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.impl.PrescriptionRequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.RESPONSE;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.SENT;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCT_ID;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.*;

public class RequestPrescriptionCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.log(Level.INFO, request.getParameter(PRODUCT_ID));
        logger.log(Level.INFO, request.getSession().getAttribute(PRODUCT_ID));
        Map<String, String> prescriptionRequestData = new HashMap<>();
        prescriptionRequestData.put(CLIENT_ID, String.valueOf(request.getSession()
                .getAttribute(USER_ID)));
        prescriptionRequestData.put(PRODUCT_ID, String.valueOf(request.getSession().getAttribute(PRODUCT_ID)));
        prescriptionRequestData.put(DOCTOR_ID, request.getParameter(USER_ID));
        prescriptionRequestData.put(RESPONSE, SENT);
        PrescriptionRequestServiceImpl prescriptionRequestService =
                PrescriptionRequestServiceImpl.getInstance();
        try {
            prescriptionRequestService.requestPrescriptionService(prescriptionRequestData);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        return new DefaultCommand().execute(request);

    }
}
