package by.mozolevskij.pharmacy.command.impl.order;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SetFullCostInSessionCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

        return null;
    }
}
