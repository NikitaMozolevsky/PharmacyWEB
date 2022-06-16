package com.example.demo.command.impl.user;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.dao.impl.ProductDaoImpl;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Queue;

import static com.example.demo.command.constant.PagePath.SHOW_USERS;
import static com.example.demo.command.constant.ProductAttribute.PRODUCT;
import static com.example.demo.command.constant.UserAttribute.USER;

public class ShowUserList implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException, DaoException {
        Router router = new Router();
        List<User> userList = UserDaoImpl.getInstance().findAll();
        request.setAttribute(USER, userList);
        router.setPage(SHOW_USERS);
        return router;
    }
}
