package com.alphahotel.controller;

import com.alphahotel.model.dao.UtilisateurDAO;
import com.alphahotel.model.entities.Role;
import com.alphahotel.model.entities.Utilisateur;
import com.alphahotel.utils.Constants;
import com.alphahotel.utils.FacesContextUtil;
import com.alphahotel.utils.JSFMessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "userManagerController")
@RequestScoped
public class UserManagementController extends AbstractController  implements Serializable {

    private List<String> rolesList = new ArrayList();
    private Utilisateur newUtilisateur= new Utilisateur();

    public UserManagementController() {
        fetchRoleList();
        newUtilisateur = new Utilisateur();
        newUtilisateur.setRole(Role.COMMERCIAL.toString());
    }


    public String nouvelUtilisateur(){

        System.out.println("------+++++-----------");
        System.out.println("------+++++-----------");
        System.out.println("------+++++-----------");
        try {
            if(newUtilisateur.getEmail() == null)
                return "index.html?faces-redirect=true";
            if(!newUtilisateur.getEmail().contains("@") || !newUtilisateur.getEmail().contains("."))
            {
                new JSFMessageUtil().sendErrorMessage("Email invalide");
                return null;
            }



            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            utilisateurDAO.save(newUtilisateur);

            new JSFMessageUtil().sendInfoMessage("Utilisateur correctement créé !");
            return "index.html?faces-redirect=true";
        }
        catch (Exception e){
            return "index.html?faces-redirect=true";
        }
    }


    public void fetchRoleList() {
        this.rolesList = Arrays.asList(Constants.roles);
    }

    public List<String> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<String> rolesList) {
        this.rolesList = rolesList;
    }

    public Utilisateur getNewUtilisateur() {
        return newUtilisateur;
    }

    public void setNewUtilisateur(Utilisateur newUtilisateur) {
        this.newUtilisateur = newUtilisateur;
    }


}