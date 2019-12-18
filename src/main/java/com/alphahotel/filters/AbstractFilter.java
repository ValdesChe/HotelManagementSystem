package com.alphahotel.filters;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**
 * Created by ValdoR on 2019-12-16.
 */
public class AbstractFilter {

    public AbstractFilter() {
        super();
    }

    protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.xhtml");
        dispatcher.forward(request, response);
    }

    protected void accessDenied(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/access-denied.xhtml");
        dispatcher.forward(request, response);
    }
}