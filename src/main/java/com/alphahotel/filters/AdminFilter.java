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

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter extends AbstractFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try{
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            HttpSession session = request.getSession();
            Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
            if( session != null && user != null ){
                if(user.isAdministrateur()){
                    chain.doFilter(req, resp);
                }
                else if(user.isCommercial()){
                    response.sendRedirect(request.getContextPath() + "/commercial/welcome.xhtml");
                }
                else if(user.isComptable()){
                    response.sendRedirect(request.getContextPath() + "/comptable/welcome.xhtml");
                }
                else{
                    accessDenied(req, resp, request);
                }
            }else{
                chain.doFilter(req, resp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
