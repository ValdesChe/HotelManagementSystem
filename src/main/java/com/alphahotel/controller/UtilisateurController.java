package com.alphahotel.controller;

import com.alphahotel.model.dao.UtilisateurDAO;
import com.alphahotel.model.entities.Utilisateur;
import com.alphahotel.utils.FacesContextUtil;

import javax.faces.bean.*;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "utilisateurController")
@SessionScoped
public class UtilisateurController extends AbstractController  implements Serializable {
    public static final String INJECTION_NAME = "#{utilisateurController}";

    private static final long serialVersionUID = 7161199492653715832L;
    private Utilisateur utilisateur;
    private List<Utilisateur> utilisateurList = new ArrayList();

    private Utilisateur selectedUtilisateur;

    public UtilisateurController() {
        fetchUtilisateurList();
    }


    public void fetchUtilisateurList() {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        this.utilisateurList = utilisateurDAO.getEntities();
    }


    public List<Utilisateur> getUtilisateurList() {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        this.utilisateurList = utilisateurDAO.getEntities();
        return this.utilisateurList;
    }

    public void setUtilisateurList(List utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    public String logOut(){
        FacesContextUtil.getRequest().getSession().invalidate();
        return "/login.xhtml";
    }


    public String admin(){
        return "/pages/protected/admin/admin.xhtml?faces-redirect=true";
    }

    public String commercial(){
        return "/pages/protected/commercial/commercial.xhtml?faces-redirect=true";
    }

    public String comptable(){
        return "/pages/protected/comptable/comptable.xhtml?faces-redirect=true";
    }
}