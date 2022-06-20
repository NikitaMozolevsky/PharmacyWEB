package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.demo.command.constant.PagePath.SHOW_PRODUCTS;

public class CreateOrderAndOrderProductCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        Router router = new Router();
        AddOrderCommand addOrderCommand = new AddOrderCommand();
        addOrderCommand.execute(request);
        AddOrderProductCommand addOrderProductCommand = new AddOrderProductCommand();
        addOrderProductCommand.execute(request);

        logger.log(Level.INFO, "order created");
        router.setPage(SHOW_PRODUCTS);
        return router;
    }
}
