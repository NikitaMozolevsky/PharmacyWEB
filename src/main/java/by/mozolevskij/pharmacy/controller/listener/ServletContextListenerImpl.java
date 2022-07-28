package by.mozolevskij.pharmacy.controller.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    static Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /*ConnectionPool.getInstance();*/
        logger.log(Level.INFO, "<--------> ContextInitialized <-------->" + sce.getServletContext().getContextPath());
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /*ConnectionPool.getInstance().destroyPool();*/
        logger.log(Level.INFO, "<--------> ContextDestroyed <-------->" + sce.getServletContext().getContextPath());
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }
}
