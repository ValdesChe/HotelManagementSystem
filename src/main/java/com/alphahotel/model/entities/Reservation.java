package com.alphahotel.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by ValdoR on 2019-12-20.
 */


@Entity
@Table(name="reservation")
@NamedQueries({
        @NamedQuery(name = "Reservation.findByStatus", query = "SELECT c  FROM Reservation r WHERE r.statut = :status_reservation")
})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name = "nomcl", nullable = false, length = 30)
    private String nomcl;

    @Column(name = "prenomcl", nullable = false, length = 30)
    private String prenomcl;

    @Column(name = "numpassort", length = 30)
    private String numeropassort;

    @Column(name = "nbnuit", length = 11)
    private int nbnuit;

    @Column(name = "total", length = 11)
    private double total;

    @Column(name = "statut", nullable = false, length = 30)
    private String statut;

    @Column(name = "date_debut")
    private Date date_debut;

    @Column(name = "date_fin")
    private Date date_fin;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "commercial_confirm", referencedColumnName = "id")
    private Utilisateur commercial_confirm;

    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "comptable_bill", referencedColumnName = "id")
    private Utilisateur comptable_bill;


    @ManyToOne(targetEntity = Chambre.class, optional = false)
    @JoinColumn(name = "chambre_id", referencedColumnName = "id")
    private Chambre chambre;

    public Reservation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomcl() {
        return nomcl;
    }

    public void setNomcl(String nomcl) {
        this.nomcl = nomcl;
    }

    public String getPrenomcl() {
        return prenomcl;
    }

    public void setPrenomcl(String prenomcl) {
        this.prenomcl = prenomcl;
    }

    public String getNumeropassort() {
        return numeropassort;
    }

    public void setNumeropassort(String numeropassort) {
        this.numeropassort = numeropassort;
    }

    public int getNbnuit() {
        return nbnuit;
    }

    public void setNbnuit(int nbnuit) {
        this.nbnuit = nbnuit;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Utilisateur getCommercial_confirm() {
        return commercial_confirm;
    }

    public void setCommercial_confirm(Utilisateur commercial_confirm) {
        this.commercial_confirm = commercial_confirm;
    }

    public Utilisateur getComptable_bill() {
        return comptable_bill;
    }

    public void setComptable_bill(Utilisateur comptable_bill) {
        this.comptable_bill = comptable_bill;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }
}


