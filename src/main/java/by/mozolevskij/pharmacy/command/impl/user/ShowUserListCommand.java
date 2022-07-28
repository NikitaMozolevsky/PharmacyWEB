package by.mozolevskij.pharmacy.command.impl.user;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.command.attribute.UserAttribute;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.dao.impl.UserDaoImpl;
import by.mozolevskij.pharmacy.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            request.setAttribute(UserAttribute.USER, userList);
            router.setPage(PagePath.SHOW_USERS);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        return router;
    }
}
