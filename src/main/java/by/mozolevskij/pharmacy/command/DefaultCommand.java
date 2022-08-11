package by.mozolevskij.pharmacy.command;

import by.mozolevskij.pharmacy.command.attribute.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.FULL_COST;

public class DefaultCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.log(Level.INFO, "full cost {}", request.getSession().getAttribute(FULL_COST));
        Router router = new Router();
        router.setPage(PagePath.MAIN_PAGE_JSP); //for example
        return router;
    }
}
