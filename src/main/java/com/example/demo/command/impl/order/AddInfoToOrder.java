package com.example.demo.command.impl.order;

import com.example.demo.command.Command;
import com.example.demo.command.Router;
import com.example.demo.exception.CommandException;
import com.example.demo.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.command.constant.OrderAttribute.ORDER_ID;
import static com.example.demo.command.constant.PagePath.SHOW_PRODUCTS;
import static com.example.demo.command.constant.UserAttribute.USER_ID;

public class AddInfoToOrder implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderServiceImpl orderService = OrderServiceImpl.getInstance();
        Router router = new Router();
        Map<String, String> orderInfo = new HashMap<>();
        orderInfo.put(USER_ID, (String) request.getSession().getAttribute(USER_ID));
        router.setPage(SHOW_PRODUCTS);
        return null;
    }
}
