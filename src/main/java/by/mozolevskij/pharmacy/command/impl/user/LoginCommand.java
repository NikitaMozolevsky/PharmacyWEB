package by.mozolevskij.pharmacy.command.impl.user;

import by.mozolevskij.pharmacy.command.attribute.DefaultAttribute;
import by.mozolevskij.pharmacy.command.attribute.OrderAttribute;
import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.exception.ServiceException;
import by.mozolevskij.pharmacy.service.UserService;
import by.mozolevskij.pharmacy.service.impl.UserServiceImpl;
import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.dao.impl.OrderDaoImpl;
import by.mozolevskij.pharmacy.dao.impl.UserDaoImpl;
import by.mozolevskij.pharmacy.entity.user.User;
import by.mozolevskij.pharmacy.util.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Enumeration;
import java.util.Optional;

import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.FULL_COST;
import static by.mozolevskij.pharmacy.command.attribute.OrderAttribute.ORDER_ID;
import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.*;

public class LoginCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router = new Router();
        Optional<User> optionalUser = Optional.empty();
        String login = request.getParameter(LOGIN); // TODO: 18.04.2022 add to separate class 2 scr.
        String password = request.getParameter(PASSWORD);
        password = PasswordEncryptor.passwordEncryption(password);
        UserService userService = UserServiceImpl.getInstance();
        UserDaoImpl userDao = new UserDaoImpl();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        /*String page;*/
        HttpSession session = request.getSession();
        try {
            optionalUser = userService.authenticate(login, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String userName = user.getUserName();
                String userId = String.valueOf(user.getUserId());
                session.setAttribute(LOGIN, login);
                session.setAttribute(USER_NAME, userName);
                session.setAttribute(USER_ID, userId);
                session.setAttribute(MONEY_AMOUNT, userDao.showMoneyAmountDao(userId));// TODO: 7/6/2022 changed to Integer from String
                Optional<String> orderId = orderDao.getOrderIdByUserIdDao(userId);
                if (orderId.isPresent()) {
                    session.setAttribute(ORDER_ID, orderId.get());
                    double fullCost = orderDao.getFullCostDao(orderId.get());
                    session.setAttribute(FULL_COST, fullCost);
                }
                else {
                    session.setAttribute(FULL_COST, INITIAL_MONEY_AMOUNT);
                }
                boolean orderForUserNotExist = orderDao.isOrderForUserIsNotExistDao(userId);
                session.setAttribute(OrderAttribute.ORDER_EXIST, orderForUserNotExist);
                router.setPage(PagePath.MAIN_PAGE);
                Enumeration<String> sessionAttributeNames = request.getSession().getAttributeNames();
                Enumeration<String> requestAttributeNames = request.getAttributeNames();
                logger.log(Level.INFO, "session attributes {} \n request attributes {}",
                        sessionAttributeNames, requestAttributeNames);
            } else {
                request.setAttribute(DefaultAttribute.LOGIN_MSG, "incorrect login or pass");
                router.setPage(PagePath.INDEX);
            }
        } catch (ServiceException | DaoException e) {
            logger.log(Level.INFO, "login exception {},", e.getMessage());
            throw new CommandException(); // TODO: 18.04.2022
        }
        return router; // TODO: 01.04.2022 Validation (in UserServiceImpl), implement Router
    }
}