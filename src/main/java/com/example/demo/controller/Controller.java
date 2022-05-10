package com.example.demo.controller;

import com.example.demo.command.Command;
import com.example.demo.command.CommandType;
import com.example.demo.command.Router;
import com.example.demo.exception.CommandException;
import com.example.demo.pool.ConnectionPool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    public static final String COMMAND = "command";
    static Logger logger = LogManager.getLogger();

    public void init() {
        logger.log(Level.INFO, "--------> ServletInit <--------" + this.getServletInfo());
        ConnectionPool.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandString = request.getParameter(COMMAND);
        logger.log(Level.INFO, "controller get command : {}", commandString);
        try {
            Command command = CommandType.define(commandString);
            Router router = command.execute(request);
            switch (router.getCurrentRouterType()) {
                case FORWARD -> {
                    logger.log(Level.INFO, "RouterType - forward, to page {}", router.getPage());
                    RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPage());
                    dispatcher.forward(request, response);
                }
                case REDIRECT -> {
                    logger.log(Level.INFO, "RouterType - redirect, to page {}", router.getPage());
                    response.sendRedirect(router.getPage());
                }
            }
            /*String mail = this.getServletContext().getInitParameter("mail");
            request.setAttribute("e-mail", mail);*/
            /*try {
                //response.sendRedirect(request.getContextPath() + "../" + page);
            } catch (CommandException e) {
                *//*response.sendError(500);*//*
                throw new ServletException(e);
                // TODO: 12.04.2022 return to index.jsp
            }*/
        }
        catch (CommandException e) {
            logger.log(Level.ERROR, "incorrect command");
            response.sendError(500);
        }
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO, "--------> ServletDestroyed <--------" + this.getServletName());
    }
}