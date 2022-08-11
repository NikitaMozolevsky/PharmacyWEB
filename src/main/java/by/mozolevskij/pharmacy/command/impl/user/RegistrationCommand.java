package by.mozolevskij.pharmacy.command.impl.user;

import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.entity.user.User;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.impl.UserServiceImpl;
import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.mozolevskij.pharmacy.command.attribute.DefaultAttribute.REGISTER_MSG;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.*;

public class RegistrationCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        Router router = new Router();

        Map<String, String> userData = new HashMap();
        userData.put(USER_NAME, request.getParameter(USER_NAME));
        userData.put(LOGIN, request.getParameter(LOGIN));
        userData.put(EMAIL, request.getParameter(EMAIL));
        userData.put(PHONE, request.getParameter(PHONE));
        userData.put(PASSWORD, request.getParameter(PASSWORD));
        try {
            Optional<User> optionalUser = userService.userExistService(userData);
            if (optionalUser.isPresent()) {
                request.setAttribute(EXISTING_USER_INFO, optionalUser.get());
                return new RegistrationPageCommand().execute(request);
            }
            //if (userService.validateUserData(userDataMap)) {
                userService.register(userData);
                //}
            logger.log(Level.INFO, "user was registered successful");
            router.setPage(PagePath.INDEX_JSP);
        } catch (ServiceException e) {
            request.setAttribute(REGISTER_MSG, "incorrect register data");
            logger.log(Level.ERROR, "user register error", e);
        }
        return router;
    }
}
