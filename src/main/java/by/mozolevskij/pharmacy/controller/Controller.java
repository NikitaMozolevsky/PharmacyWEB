package by.mozolevskij.pharmacy.controller;

import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.pool.ConnectionPool;
import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.CommandType;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.exception.CommandException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
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
                    logger.log(Level.INFO, "RouterType - forward, to page {}", router.getCurrentPage());
                    RequestDispatcher dispatcher = request.getRequestDispatcher(router.getCurrentPage());
                    dispatcher.forward(request, response);
                }
                case REDIRECT -> {
                    logger.log(Level.INFO, "RouterType - redirect, to page {}", router.getCurrentPage());
                    response.sendRedirect(router.getCurrentPage());
                }
            }
        }
        catch (CommandException e) {
            logger.log(Level.ERROR, "incorrect command");
            response.sendError(500);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException from where?");
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