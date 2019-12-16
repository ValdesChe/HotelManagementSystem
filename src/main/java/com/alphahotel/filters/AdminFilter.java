package com.alphahotel.filters;

import com.alphahotel.model.entities.Utilisateur;

import javax.servlet.*;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by ValdoR on 2019-12-16.
 */

@WebFilter(urlPatterns = "/pages/protected/admin/*" ,servletNames = "{Faces Servlet}")
public class AdminFilter extends AbstractFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        Utilisateur user = (Utilisateur) request.getSession(true).getAttribute("utilisateur");
        if(user != null){
            if(!user.isAdministrateur()){
                accessDenied(req, resp, request);
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
