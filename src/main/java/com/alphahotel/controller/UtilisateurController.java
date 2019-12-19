package com.alphahotel.controller;

import com.alphahotel.model.dao.UtilisateurDAO;
import com.alphahotel.model.entities.Role;
import com.alphahotel.model.entities.Utilisateur;
import com.alphahotel.utils.Constants;
import com.alphahotel.utils.FacesContextUtil;
import com.alphahotel.utils.JSFMessageUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "utilisateurController")
@SessionScoped
public class UtilisateurController extends AbstractController  implements Serializable {
    public static final String INJECTION_NAME = "#{utilisateurController}";

    private static final long serialVersionUID = 7161199492653715832L;
    private Utilisateur utilisateur;

    public UtilisateurController() {
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String logOut(){
        FacesContextUtil.getRequest().getSession().invalidate();
        return "/login.xhtml?faces-redirect=true";
    }


    public String admin(){
        return "/admin/welcome.xhtml?faces-redirect=true";
    }

    public String commercial(){
        return "/pages/protected/commercial/commercial.xhtml?faces-redirect=true";
    }

    public String comptable(){
        return "/pages/protected/comptable/comptable.xhtml?faces-redirect=true";
    }
}