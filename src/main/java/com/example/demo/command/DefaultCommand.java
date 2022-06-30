package com.example.demo.command;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.demo.command.attribute.PagePath.MAIN_PAGE;

public class DefaultCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPage(MAIN_PAGE); //for example
        return router;
    }
}
