package com.alphahotel.filters;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ValdoR on 2019-12-16.
 */
public class AbstractFilter {

    public AbstractFilter() {
        super();
    }

    protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        /*RequestDispatcher dispatcher = req.getRequestDispatcher("/login.xhtml?faces-redirect=true");
        dispatcher.forward(request, response);*/
        httpServletResponse.sendRedirect("/login.xhtml");
    }

    protected void accessDenied(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/access-denied.xhtml?faces-redirect=true");
        dispatcher.forward(request, response);
    }
}