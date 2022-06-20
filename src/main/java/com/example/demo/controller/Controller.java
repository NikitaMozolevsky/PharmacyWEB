package com.example.demo.controller;

import com.example.demo.command.Command;
import com.example.demo.command.CommandType;
import com.example.demo.command.Router;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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
        }
        catch (CommandException e) {
            logger.log(Level.ERROR, "incorrect command");
            response.sendError(500);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException, perhaps in showAllUsers findAll()");
            response.sendError(500);
        } catch (ServiceException e) {
            response.sendError(500);
        }
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO, "--------> ServletDestroyed <--------" + this.getServletName());
    }

    //#Wed Jun 01 16:02:29 MSK 2022
    //useJDBCCompliantTimezoneShift=true
    //useLegacyDatetimeCode=true
    //autoReconnect=true
    //url=jdbc\:mysql\://localhost\:3306/mydb
    //useSSL=true
    //password=1111
    //serverSslCert=classpath\:server.crt
    //useUnicode=true
    //driver=com.mysql.cj.jdbc.Driver
    //serverTimezone=UTC
    //characterEncoding=UTF-8
    //user=root
}