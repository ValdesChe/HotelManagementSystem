package com.alphahotel.controller;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.alphahotel.model.dao.ReservationDAO;
import com.alphahotel.model.entities.Reservation;
import com.alphahotel.model.entities.ReservationStatus;
import com.alphahotel.utils.FilePrinterUtil;
import com.alphahotel.utils.Helpers;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "facturationController")
@RequestScoped
public class FacturationController extends AbstractController  implements Serializable {
    private static final long serialVersionUID = -1;
    private List<Reservation> uniqueReservationToPrint;

    private JRBeanCollectionDataSource beanCollectionDataSource;
    private List<Reservation> listOfReservationsToPrint;

    @ManagedProperty(value="#{loginController}")
    private LoginController loginController; // +setter

    private ReservationDAO reservationDAO;
    private HashMap<String, Object> parameters;
    public FacturationController() {
        reservationDAO = new ReservationDAO();
    }
    /**
     * Preparing an output stream of the generated PDF invoice.
     *
     * @return
     */
    public String printReservation(Reservation reservation) throws JRException, IOException {
        /*JasperCompileManager.compileReportToFile(
                "D:/CI3-Workspace/JavaEE/PROJET++/HotelManagementSystem/src/main/webapp/commercial/invoice.jrxml", // the path to the jrxml file to compile
                "D:/CI3-Workspace/JavaEE/PROJET++/HotelManagementSystem/src/main/webapp/commercial/invoice.jasper");
        */

        beanCollectionDataSource = new JRBeanCollectionDataSource(uniqueReservationToPrint);
        parameters = new HashMap<>();
        parameters.put("invoiceNo", "FACT"+reservation.getId());
        parameters.put("billingCompanyName", "M./Mme "
                + reservation.getNomcl()
                + " " +reservation.getPrenomcl());
        parameters.put("billingCompanyAddress", reservation.getNumeropassport());
        parameters.put("billingCompanyFirstState", reservation.getEmail());
        parameters.put("billingCompanySecondState", reservation.getTelephone());


        parameters.put("shippingName", "M./Mme "
                + reservation.getCommercial_confirm().getPrenom()
                + " " +reservation.getCommercial_confirm().getPrenom());
        parameters.put("shippingAddress", "-");
        parameters.put("shippingFirstState","-");
        parameters.put("shippingSecondState","-");
        parameters.put("IMAGE_PATH",
                FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images"));


        // Reservation parameters
        parameters.put("id", "FACT"+reservation.getId());
        parameters.put("date_debut", reservation.getDate_debut());
        parameters.put("date_fin", reservation.getDate_fin());
        parameters.put("nbnuit", reservation.getNbnuit());
        parameters.put("total", reservation.getTotal());
        Date date = null;
        try {
            date = Helpers.formatDateOrFail(Helpers.actualDateTime());
            reservation.setUpdated_at(date);
            reservation.setStatut(ReservationStatus.ENDED.toString());
            reservationDAO.update(reservation);
            FilePrinterUtil.generateFile("PDF",
                    "/reports/invoice.jasper",
                    "Facture_".concat(String.valueOf(reservation.getId()))
                            .concat("_" +reservation.getStatut())
                            .concat("_" +reservation.getPrenomcl())
                            .concat("_" +reservation.getNomcl())
                            .concat(".pdf"),
                    parameters
            );

            displayInfoMessage("Reservation confirmée avec succès !");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retourne la liste des factures à imprimer
     * @return
     */
    public List<Reservation> getUniqueReservationToPrint() {
        return uniqueReservationToPrint;
    }

    public void setUniqueReservationToPrint(List<Reservation> uniqueReservationToPrint) {
        this.uniqueReservationToPrint = uniqueReservationToPrint;
    }

    public List<Reservation> getListOfReservationsToPrint() {
        return reservationDAO.findByStatus(ReservationStatus.TO_PRINT.toString());
    }

    public void setListOfReservationsToPrint(List<Reservation> listOfReservationsToPrint) {
        this.listOfReservationsToPrint = listOfReservationsToPrint;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
