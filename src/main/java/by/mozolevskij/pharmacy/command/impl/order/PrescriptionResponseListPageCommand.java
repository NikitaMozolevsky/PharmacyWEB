package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.entity.prescription_request.PrescriptionRequest;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;

import static by.mozolevskij.pharmacy.command.attribute.Message.PRESCRIPTION_REQUEST_LIST_EMPTY_MSG;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.*;
import static by.mozolevskij.pharmacy.command.attribute.PagePath.*;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.USER_ID;

public class PrescriptionResponseListPageCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String clientId = String.valueOf(request.getSession().getAttribute(USER_ID));
        try {
            Optional<ArrayList<PrescriptionRequest>> optionalPrescriptionResponseList =
                    OrderDaoImpl.getInstance().getResponsePrescriptionListDao(clientId);
            ArrayList<PrescriptionRequest> prescriptionRequests =
                    optionalPrescriptionResponseList.get();
            if (prescriptionRequests.isEmpty()) {
                request.setAttribute(PRESCRIPTION_REQUEST_LIST_EMPTY,
                        PRESCRIPTION_REQUEST_LIST_EMPTY_MSG);
                router.setPage(MAIN_PAGE_JSP);
            }
            else {
                request.setAttribute(PRESCRIPTION_REQUEST_LIST, prescriptionRequests);
                router.setPage(GET_PRESCRIPTION_RESPONSE_LIST_JSP);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }

        return router;
    }
}
