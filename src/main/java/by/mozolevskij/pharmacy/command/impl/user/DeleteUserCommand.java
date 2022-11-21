package by.mozolevskij.pharmacy.command.impl.user;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.dao.impl.UserDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;

import static by.mozolevskij.pharmacy.command.attribute.UserAttribute.USER_ID;

public class DeleteUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            userDao.delete(userId);
        } catch (DaoException e) {
            throw new CommandException();
        }
        return new ShowUserListCommand().execute(request);
    }
}
