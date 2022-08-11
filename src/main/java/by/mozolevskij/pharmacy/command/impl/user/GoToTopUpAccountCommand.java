package by.mozolevskij.pharmacy.command.impl.user;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToTopUpAccountCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.log(Level.INFO, "top up account beginning");
        Router router = new Router();
        router.setPage(PagePath.TOP_UP_ACCOUNT_JSP);
        logger.log(Level.INFO, "top up account ending");
        return router;
    }
}
