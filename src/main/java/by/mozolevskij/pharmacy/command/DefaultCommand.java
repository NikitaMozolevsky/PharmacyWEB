package by.mozolevskij.pharmacy.command;

import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.command.impl.order.SetFullCostInSessionCommand;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.FULL_COST;

public class DefaultCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.log(Level.INFO, "full cost {}", request.getSession().getAttribute(FULL_COST));
        new SetFullCostInSessionCommand().execute(request);
        Router router = new Router();
        router.setCurrentPage(PagePath.MAIN_PAGE_JSP); //for example
        return router;
    }
}
