package com.alphahotel.controller;

import com.alphahotel.model.entities.Utilisateur;
import com.alphahotel.utils.FacesContextUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created by ValdoR on 2019-12-12.
 */
@RequestScoped
@ManagedBean
public class UtilisateurController extends AbstractController {
    public static final String INJECTION_NAME = "#{utilisateurController}";
    private Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String logOut(){
        FacesContextUtil.getRequest().getSession().invalidate();
        return "login.xhtml";
    }

    public String admin(){
        return "/pages/protected/admin/commercial.xhtml?faces-redirect=true";
    }

    public String commercial(){
        return "/pages/protected/commercial/commercial.xhtml?faces-redirect=true";
    }

    public String comptable(){
        return "/pages/protected/comptable/comptable.xhtml?faces-redirect=true";
    }
}