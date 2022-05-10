package com.example.demo.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

//@WebFilter(filterName = "ForwardFilter", dispatcherTypes = { DispatcherType.FORWARD }, urlPatterns = "/pages/*")
public class ForwardFilter implements Filter {

    static Logger logger = LogManager.getLogger();

    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        logger.log(Level.INFO, "Session in ForwardFilter" + (session != null ? session.getId() : "the session is abcent"));
        /*session.setAttribute("filter_attr", DispatcherType.FORWARD);*/
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
