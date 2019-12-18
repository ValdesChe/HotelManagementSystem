package com.alphahotel.filters;

import com.alphahotel.model.entities.Utilisateur;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ValdoR on 2019-12-16.
 */

@WebFilter(urlPatterns = "/pages/*" ,servletNames = "{Faces Servlet}")
public class LoginFilter extends AbstractFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
        if( user == null){
            doLogin(req, resp, request);
            return;
        }
        chain.doFilter(req, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
