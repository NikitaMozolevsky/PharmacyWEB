package by.mozolevskij.pharmacy.command.impl.user;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.command.attribute.UserAttribute;
import by.mozolevskij.pharmacy.entity.user.AccessLevel;
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

import static by.mozolevskij.pharmacy.command.attribute.PagePath.REQUEST_PRESCRIPTION_JSP;
import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCT_ID;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.ROLE;

public class ShowUserListCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.log(Level.INFO, request.getParameter(PRODUCT_ID));
        logger.log(Level.INFO, request.getSession().getAttribute(PRODUCT_ID));
        Router router = new Router();
        List<Optional<User>> userListOptional;
        Optional<String> usersRole = Optional.ofNullable(request.getParameter(ROLE));
        try {
            List<User> userList = new ArrayList<>();

            if (usersRole.isPresent()) {
                Optional<AccessLevel> accessLevel = Optional.of(AccessLevel.valueOf
                        (usersRole.get()));
                userListOptional = UserDaoImpl.getInstance().findAllByAccessLevel(accessLevel.get());
                switch (accessLevel.get()) {
                    case CLIENT: router.setCurrentPage("not created");
                        break;

                    case DOCTOR: router.setCurrentPage(REQUEST_PRESCRIPTION_JSP);
                        break;
                }
            }
            else {
                userListOptional = UserDaoImpl.getInstance().findAll();
                router.setCurrentPage(PagePath.SHOW_USERS_JSP);
            }
            for (Optional<User> optionalUser : userListOptional) {
                optionalUser.ifPresent(userList::add);//IDEA
            }
            request.setAttribute(UserAttribute.USER, userList);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException();
        }
        return router;
    }
}
