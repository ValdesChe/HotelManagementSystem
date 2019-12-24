package com.alphahotel.filters;

import com.alphahotel.model.entities.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ValdoR on 2019-12-16.
 */

public class CommercialFilter extends AbstractFilter implements Filter {
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
                System.out.println("--- COMMERCIAL FILTER DETECT USER-");
                System.out.println("++++++++++");
                if(user.isCommercial() && reqURI.contains("commercial")){
                    chain.doFilter(req, resp);
                }
                if(user.isAdministrateur()){
                    response.sendRedirect(request.getContextPath() + "/admin/welcome.xhtml");
                }
                if(user.isComptable()){
                    response.sendRedirect(request.getContextPath() + "/comptable/welcome.xhtml");
                }

            }else{

                System.out.println("++++++++++");
                System.out.println("--- CHAIN FILTER ON COMMERCIAL-");
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
