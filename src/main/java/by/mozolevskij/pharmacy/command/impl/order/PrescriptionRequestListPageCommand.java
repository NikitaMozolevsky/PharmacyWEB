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

import java.util.*;

import static by.mozolevskij.pharmacy.command.attribute.Message.PRESCRIPTION_REQUEST_LIST_EMPTY_MSG;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PRESCRIPTION_REQUEST_LIST;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.PRESCRIPTION_REQUEST_LIST_EMPTY;
import static by.mozolevskij.pharmacy.command.attribute.PagePath.GET_PRESCRIPTION_REQUEST_LIST_JSP;
import static by.mozolevskij.pharmacy.command.attribute.PagePath.MAIN_PAGE_JSP;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.USER_ID;

public class PrescriptionRequestListPageCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String doctorId = String.valueOf(request.getSession().getAttribute(USER_ID));
        try {
            Optional<ArrayList<PrescriptionRequest>> prescriptionRequestList =
                    OrderDaoImpl.getInstance().getRequestPrescriptionListDao(doctorId);
            if (!prescriptionRequestList.get().isEmpty()) {
                request.setAttribute(PRESCRIPTION_REQUEST_LIST, prescriptionRequestList.get());
                router.setCurrentPage(GET_PRESCRIPTION_REQUEST_LIST_JSP);
            }
            else {
                request.setAttribute(PRESCRIPTION_REQUEST_LIST_EMPTY,
                        PRESCRIPTION_REQUEST_LIST_EMPTY_MSG);
                router.setCurrentPage(MAIN_PAGE_JSP);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }

        return router;
    }
}
