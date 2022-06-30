package com.example.demo.command.impl.user;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.user.User;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.command.attribute.PagePath.SHOW_USERS;
import static com.example.demo.command.attribute.UserAttribute.USER;

public class ShowUserListCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        List<Optional<User>> userListOptional;
        try {
            List<User> userList = new ArrayList<>();
            userListOptional = UserDaoImpl.getInstance().findAll();
            for (int i = 0; i < userListOptional.size(); i++) {
                Optional<User> user = userListOptional.get(i);
                userList.add(user.get());
            }
            request.setAttribute(USER, userList);
            router.setPage(SHOW_USERS);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        return router;
    }
}
