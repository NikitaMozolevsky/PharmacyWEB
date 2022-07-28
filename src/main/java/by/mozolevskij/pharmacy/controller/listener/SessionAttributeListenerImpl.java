package by.mozolevskij.pharmacy.controller.listener;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {

    static Logger logger = LogManager.getLogger();

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        logger.log(Level.INFO, "<--------> AttributeAdded <-------->" + sbe.getSession().getAttribute("user_name"));
        logger.log(Level.INFO, "<--------> AttributeAdded <-------->" + sbe.getSession().getAttribute("current_page"));

        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        logger.log(Level.INFO, "<--------> AttributeReplaced <-------->" + sbe.getSession().getAttribute("user_name"));
        logger.log(Level.INFO, "<--------> AttributeReplaced <-------->" + sbe.getSession().getAttribute("current_page"));
        /* This method is called when an attribute is replaced in a session. */
    }
}
