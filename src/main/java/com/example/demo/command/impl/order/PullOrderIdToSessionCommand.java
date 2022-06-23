package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.dao.impl.OrderDaoImpl;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static com.example.demo.command.constant.OrderAttribute.ORDER_ID;

public class PullOrderIdToSessionCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException, DaoException, ServiceException, SQLException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        request.getSession().setAttribute(ORDER_ID, orderDao.getOrderID(request));
        return null;
    }
}
