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

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController extends AbstractController {

    @ManagedProperty(value = UtilisateurController.INJECTION_NAME)
    private UtilisateurController utilisateurController;

    private String loginOrEmail;
    private String password;

    public void setUtilisateurController(UtilisateurController utilisateurController) {
        this.utilisateurController = utilisateurController;
    }

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

    private boolean isEmail(String value) {
        return value.contains("@");
    }

    // Fonction de connection utilisateur
    public String seConnecter() {
        Utilisateur utilisateur = isValidLogin(loginOrEmail, password);

        System.out.println("LOGINNNNNNNNNN INTRO");
        if (utilisateur != null) {
            System.out.println("LOGINNNNNNNNNN" + utilisateur.getRole());
            System.out.println("LOGINNNNNNNNNN" + utilisateur.getId());
            utilisateurController.setUtilisateur(utilisateur);
            FacesContextUtil.getRequest().getSession().setAttribute("utilisateur", utilisateur);
            if (utilisateur.isCommercial())
                return utilisateurController.commercial();

            if (utilisateur.isComptable())
                return utilisateurController.comptable();

            if (utilisateur.isAdministrateur())
                return utilisateurController.admin();

            return "index.xhtml";
        }
        displayErrorMessage("Check your login/password");
        return null;
    }


    public String seDeconnecter(){
        FacesContextUtil.getRequest().getSession().invalidate();
        return "/login.xhtml?faces-redirect=true";
    }

}