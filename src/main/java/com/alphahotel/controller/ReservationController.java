package com.alphahotel.controller;

import com.alphahotel.model.dao.ReservationDAO;
import com.alphahotel.model.entities.Reservation;
import com.alphahotel.model.entities.ReservationStatus;
import com.alphahotel.utils.Constants;
import com.alphahotel.utils.FacesContextUtil;
import com.alphahotel.utils.Helpers;
import com.alphahotel.utils.Mailer;

import javax.faces.bean.*;
import java.io.Serializable;
import java.text.ParseException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "reservationController")
@RequestScoped
public class ReservationController extends AbstractController  implements Serializable {
    private final List<String> reservationStatus = Arrays.asList(Constants.reservationStatusList);
    private Reservation reservation;
    private ReservationDAO reservationDAO;
    private long nbnuit = 0;

    @ManagedProperty(value="#{loginController}")
    private LoginController loginController; // +setter

    private List<Reservation> reservationList;

    public ReservationController() {
        reservationDAO = new ReservationDAO();
    }


    public String creerReservation() throws ParseException {
        if (isValidReservation(reservation) ) {
            Date date = Helpers.formatDateOrFail(Helpers.actualDateTime());
            reservation.setCreated_at(date);
            reservation.setUpdated_at(date);
            reservation.setStatut(ReservationStatus.PENDING.toString());
            reservation.setNbnuit(Math.toIntExact(Helpers.diffDatesInDays(reservation.getDate_debut(), reservation.getDate_fin())));
            try {
                reservationDAO.save(reservation);
                reservation = new Reservation();
                displayInfoMessage("Reservation créée avec succès !");
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

    public void confirmReservation(Reservation reservation) throws ParseException {
        if (isValidReservation(reservation)) {
            Date date = Helpers.formatDateOrFail(Helpers.actualDateTime());
            reservation.setUpdated_at(date);
            reservation.setStatut(ReservationStatus.CONFIRM.toString());
            reservation.setCommercial_confirm(loginController.getUtilisateur());
            try {
                String body = "&nbsp; Bonjour M/Mme <b>"+ reservation.getNomcl() + "</b><br/>"
                        + "&nbsp; Nous vous informons par ce message que votre reservaton de chambre <br/>"
                        + " allant du " + reservation.getDate_debut().toString() + " au "
                        + reservation.getDate_fin().toString() + " (" + reservation.getNbnuit() + " nuits )a été confirmée en ce jour. <br/>"
                        + "&nbsp; Nous vous souhaitons un agréable séjour chez "
                        + "<a href=\"www.alphahotel.com\" title=\"Visitez notre site\"> ALPHA HOTEL </a>" +
                        " et nous esperons vous voir d'ici peu chez nous !<br/><br/>"
                        +"Reception ALPHA HOTEL par" + loginController.getUtilisateur().getPrenom() +"<br/><br/><br/><br/>"
                        + "Cordialement,";
                String subject = "CONFIRMATION RESERVATION HOTEL ALPHA";
                Mailer javaEmail = new Mailer();

                String [] table = new String[1];
                table[0] = reservation.getEmail();

                javaEmail.setMailServerProperties();
                javaEmail.createEmailMessage(table, subject, body);
                javaEmail.sendEmail();
                reservationDAO.update(reservation);
                displayInfoMessage("Reservation confirmée avec succès !");
                FacesContextUtil.redirect("/commercial/reservations.xhtml");
            }
            catch (Exception e){
                e.printStackTrace();
                displayErrorMessage("Une érreur est survenue lors de la confirmation de la reservation!");
            }
        } else {
            displayErrorMessage("La reservation a déja expirée !");
        }
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

    public List<Reservation> getReservationList() {
        return reservationDAO.findByStatus(ReservationStatus.PENDING.toString());
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}