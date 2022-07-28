package by.mozolevskij.pharmacy.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.mozolevskij.pharmacy.command.attribute.DefaultAttribute.TEXT_HTML;
import static by.mozolevskij.pharmacy.command.attribute.DefaultAttribute.UTF_8;

@WebFilter(filterName = "CharEncodingFilter", urlPatterns = "/controller")
public class CharEncodingFilter implements Filter {

    static Logger logger = LogManager.getLogger();

    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        response.setContentType(TEXT_HTML);
        request.setCharacterEncoding(UTF_8);
        HttpSession session = servletRequest.getSession();
        logger.log(Level.INFO, "--------> Session in CharEncodingFilter <--------" + (session != null ? session.getId() : "session not created"));
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
