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


public class AdminFilter extends AbstractFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try{
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            HttpSession session = request.getSession();
            Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");

            String reqURI = request.getRequestURI();
            System.out.println("request URI = " + reqURI);
            if( session != null && user != null ){
                System.out.println("++++++++++");
                System.out.println("--- ADMIN FILTER DETECT USER-");
                System.out.println("++++++++++");
                if(user.isAdministrateur() && reqURI.contains("admin")){
                    chain.doFilter(req, resp);
                }
                if(user.isCommercial()){
                    response.sendRedirect(request.getContextPath() + "/commercial/welcome.xhtml?faces-redirect=true");
                }
                if(user.isComptable()){
                    response.sendRedirect(request.getContextPath() + "/comptable/welcome.xhtml?faces-redirect=true");
                }

            }else{

                System.out.println("++++++++++");
                System.out.println("--- CHAIN FILTER ON ADMIN-");
                System.out.println("++++++++++");
                doLogin(req, resp, request);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
