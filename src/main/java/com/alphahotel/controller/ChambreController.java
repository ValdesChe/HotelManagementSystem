package com.alphahotel.controller;

import com.alphahotel.model.dao.ChambreDAO;
import com.alphahotel.model.entities.*;
import com.alphahotel.model.entities.Chambre;
import com.alphahotel.utils.Constants;
import com.alphahotel.utils.Helpers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "chambreController")
@RequestScoped
public class ChambreController extends AbstractController  implements Serializable {

    private List<String> bedroomTypes = Arrays.asList(Constants.bedroomTypes);
    private List<String> chambreStatusList = Arrays.asList(Constants.chambreStatusList);
    private List<Chambre> listChambres = new ArrayList();
    private Chambre selectedChambre;


    @ManagedProperty(value="#{loginController}")
    private LoginController loginController; // +setter
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void fetchChambreList() {
        ChambreDAO chambreDAO = new ChambreDAO();
        this.listChambres = chambreDAO.getEntities();
    }

    public ChambreController() {
        selectedChambre = new Chambre();
        fetchChambreList();
    }

    public List<String> getBedroomTypes() {
        return bedroomTypes;
    }

    public void setBedroomTypes(List<String> bedroomTypes) {
        this.bedroomTypes = bedroomTypes;
    }

    public List<String> getChambreStatusList() {
        return chambreStatusList;
    }

    public void setChambreStatusList(List<String> chambreStatusList) {
        this.chambreStatusList = chambreStatusList;
    }

    public List<Chambre> getListChambres() {
        fetchChambreList();
        return listChambres;
    }

    public void setListChambres(List<Chambre> listChambres) {
        this.listChambres = listChambres;
    }

    public Chambre getSelectedChambre() {
        return selectedChambre;
    }

    public void setSelectedChambre(Chambre selectedChambre) {
        this.selectedChambre = selectedChambre;
    }

    public String nouvelleChambre(){
        ChambreDAO chambreDAO =new ChambreDAO();
        if (selectedChambre != null ) {
            selectedChambre.setUtilisateur(loginController.getUtilisateur());
            try {
                Date date = Helpers.formatDateOrFail(Helpers.actualDateTime());
                selectedChambre.setCreated_at(date);
                selectedChambre.setUpdated_at(date);
                chambreDAO.save(selectedChambre);
                displayInfoMessage("Chambre créée avec succès !");
                selectedChambre = new Chambre();
            }
            catch (Exception e){
                e.printStackTrace();
                displayErrorMessage("Une erreur est survenue lors de la création de la chambre!");
            }
        } else {
            displayErrorMessage("Erreur inattendue lors de la création de la chambre");
        }

        return null;
    }

}