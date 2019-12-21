package com.alphahotel.controller;

import com.alphahotel.model.dao.ReservationDAO;
import com.alphahotel.model.entities.Reservation;
import com.alphahotel.model.entities.ReservationStatus;
import com.alphahotel.utils.Constants;
import com.alphahotel.utils.Helpers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "reservationController")
@SessionScoped
public class ReservationController extends AbstractController  implements Serializable {
    private final List<String> reservationStatus = Arrays.asList(Constants.reservationStatusList);
    private Reservation reservation;

    private long nbnuit = 0;

    @ManagedProperty(value="#{loginController}")
    private LoginController loginController; // +setter
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void fetchReservationList() {
        ReservationDAO reservationDAO = new ReservationDAO();
    }

    /*
    public void setReservationStartDate(AjaxBehaviorEvent event)
            throws AbortProcessingException, ParseException {
        Date date = Helpers.formatDateOrFail(new Date().toString());

        if (date_debut.trim().length() > 0) {
            date = Helpers.formatDateOrFail(date_debut);
        }
        reservation.setDate_debut(date);
    }

    public void setReservationEndDate(AjaxBehaviorEvent event)
            throws AbortProcessingException, ParseException {
        Date date = Helpers.formatDateOrFail(new Date().toString());
        System.out.println("bOooooooooooooo");
        if (date_fin.trim().length() > 0) {
            date = Helpers.formatDateOrFail(date_fin);
        }
        reservation.setDate_fin(date);
    }

    */

    public ReservationController() {
        reservation = new Reservation();
    }

    public String creerReservation() throws ParseException {
        ReservationDAO reservationDAO = new ReservationDAO();
        if (isValidReservation(reservation) ) {
            Date date = Helpers.formatDateOrFail(Helpers.actualDateTime());
            reservation.setCreated_at(date);
            reservation.setUpdated_at(date);
            reservation.setStatut(ReservationStatus.PENDING.toString());
            reservation.setNbnuit(Math.toIntExact(Helpers.diffDatesInDays(reservation.getDate_debut(), reservation.getDate_fin())));
            try {
                reservationDAO.save(reservation);
                displayInfoMessage("Reservation créée avec succès !");
                reservation = new Reservation();
            }
            catch (Exception e){
                e.printStackTrace();
                displayErrorMessage("Une érreur est survenue lors de la création de la reservation!");
            }
        } else {
            displayErrorMessage("Erreur inattendue lors de la création de la reservation");
        }

        return null;
    }



    private boolean isValidReservation(Reservation reservation) {

        if(reservation.getDate_fin() == null || reservation.getDate_debut() == null){
            displayErrorMessage("Dates incorrectes ou non renseignées !");
            return false;
        }

        long diffDebutFinMillies =  reservation.getDate_fin().getTime() -reservation.getDate_debut().getTime();
        long diffDebutDateTodayMillies = reservation.getDate_debut().getTime() - Date.from(Instant.now()).getTime();

        if(Helpers.diffDatesInDays(reservation.getDate_debut(), Date.from(Instant.now())) < 0
                || diffDebutDateTodayMillies < 0 ){
            displayErrorMessage("Date de début incorrecte !");
            return false;
        }

        if(Helpers.diffDatesInDays(reservation.getDate_debut(), reservation.getDate_fin()) < 0
                || diffDebutFinMillies < 0 ){
            displayErrorMessage("Dates de début et de fin de séjour incorrectes !");
            return false;
        }

        if(reservation.getNomcl() == null || reservation.getNomcl().trim().isEmpty() || reservation.getPrenomcl().trim().isEmpty()){
            displayErrorMessage("Nom ou prénom invalide !");
            return false;
        }

        if(reservation.getNumeropassport() == null || reservation.getNumeropassport().trim().isEmpty()){
            displayErrorMessage("Numero de passport invalide !");
            return false;
        }

        if(!Helpers.isValidEmail(reservation.getEmail())){
            displayErrorMessage("Votre email est invalide !");
            return false;
        }

        if(reservation.getTelephone() == null || reservation.getTelephone().trim().isEmpty() || reservation.getTelephone().trim().length() < 8){
            displayErrorMessage("Numero de téléphone invalide !");
            return false;
        }
        return true;
    }

    public List<String> getReservationStatus() {
        return reservationStatus;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public long getNbnuit() {
        return nbnuit;
    }

    public void setNbnuit(long nbnuit) {
        this.nbnuit = nbnuit;
    }

    public LoginController getLoginController() {
        return loginController;
    }
}