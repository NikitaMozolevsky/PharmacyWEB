package com.example.demo.command.impl.user;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.demo.command.constant.PagePath.INDEX;

public class LogoutCommand implements Command {

    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().invalidate();
        router.setPage(INDEX);
        return router;
    }
}
