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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "userManagerController")
@SessionScoped
public class UserManagementController extends AbstractController  implements Serializable {

    private List<String> rolesList = new ArrayList();
    private List<String> acountStatusList = new ArrayList();
    private List<Utilisateur> utilisateurList = new ArrayList();
    private Utilisateur newUtilisateur= new Utilisateur();
    private Utilisateur selectedUtilisateur= new Utilisateur();


    public UserManagementController() {
        fetchRoleList();
        fetchAccountStatusList();
        fetchUtilisateurList();
        newUtilisateur.setRole(Role.COMMERCIAL.toString());
    }

    public Utilisateur getSelectedUtilisateur() {
        return selectedUtilisateur;
    }

    public void setSelectedUtilisateur(Utilisateur selectedUtilisateur) {
        this.selectedUtilisateur = selectedUtilisateur;
    }

    public void fetchUtilisateurList() {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        this.utilisateurList = utilisateurDAO.getEntities();
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

    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    public void fetchRoleList() {
        this.rolesList = Arrays.asList(Constants.roles);
    }

    public void fetchAccountStatusList() {
        this.acountStatusList = Arrays.asList(Constants.statusList);
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

    public List<String> getAcountStatusList() {
        return acountStatusList;
    }

    public void setAcountStatusList(List<String> acountStatusList) {
        this.acountStatusList = acountStatusList;
    }

    public void editUser(Utilisateur utilisateur) {
        setSelectedUtilisateur(utilisateur);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect("/admin/edituser.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String updateUtilisateur() {
        UtilisateurDAO utilisateurDAO =new UtilisateurDAO();
        try {
            selectedUtilisateur.setUpdated_at(Date.from(Instant.now()));
            utilisateurDAO.update(selectedUtilisateur);
            fetchUtilisateurList();
            displayInfoMessage("La mise à jour éffectuée avec succès !");
        }
        catch (Exception e){
            displayErrorMessage("Une erreur est survenue lors de la mise à jour !");
        }
        return null;
    }
}