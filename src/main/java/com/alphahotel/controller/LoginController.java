package com.alphahotel.controller;

import com.alphahotel.model.dao.HibernateDAO;
import com.alphahotel.model.dao.UtilisateurDAO;
import com.alphahotel.model.entities.Role;
import com.alphahotel.model.entities.Utilisateur;
import com.alphahotel.utils.ConverterSHA1;
import com.alphahotel.utils.FacesContextUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController extends AbstractController implements Serializable {

    private String loginOrEmail;
    private String password;

    private Utilisateur utilisateur = null;

    public String getLogin() {
        return loginOrEmail;
    }

    public void setLogin(String login) {
        this.loginOrEmail = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Utilisateur isValidLogin(String loginOrEmail, String password) {
        Utilisateur utilisateur;

        if (isEmail(loginOrEmail)) {
            utilisateur = new UtilisateurDAO().findByEmail(loginOrEmail);
        } else {
            utilisateur = new UtilisateurDAO().findByLogin(loginOrEmail);
        }

        if (utilisateur == null || !ConverterSHA1.cipher(password).equalsIgnoreCase((utilisateur.getPassword())) ) {
            return null;
        }
        return utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getLoginOrEmail() {
        return loginOrEmail;
    }

    public void setLoginOrEmail(String loginOrEmail) {
        this.loginOrEmail = loginOrEmail;
    }

    private boolean isEmail(String value) {
        return value.contains("@");
    }

    // Fonction de connection utilisateur
    public String seConnecter() {
        utilisateur = isValidLogin(loginOrEmail, password);
        if (utilisateur != null) {

            FacesContextUtil.getRequest().getSession().setAttribute("utilisateur", utilisateur);
            if (utilisateur.isCommercial())
                return commercial();

            if (utilisateur.isComptable())
                return comptable();

            if (utilisateur.isAdministrateur())
                return admin();

            return "index.xhtml";
        }
        displayErrorMessage("Check your login/password");
        return null;
    }


    public String seDeconnecter(){
        FacesContextUtil.getRequest().getSession().invalidate();
        return "/login.xhtml?faces-redirect=true";
    }

    public String admin(){
        return "/admin/welcome.xhtml?faces-redirect=true";
    }

    public String commercial(){
        return "/commercial/welcome.xhtml?faces-redirect=true";
    }

    public String comptable(){
        return "/comptable/welcome.xhtml?faces-redirect=true";
    }
}